package net.lycee.web.enquete.live;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;

import java.util.List;
import java.util.Objects;

@Slf4j
public class QesWebSocketHandler extends WebSocketHandlerDecorator {

    @Autowired
    private String headerUserId;
    @Autowired
    private String headerSpaceId;

    private final QesWebSocketInterceptor interceptor;

    private final String endpoint;

    public QesWebSocketHandler(
            WebSocketHandler delegate,
            QesWebSocketInterceptor interceptor,
            String endpoint
    ) {
        super(delegate);
        this.interceptor = interceptor;
        this.endpoint = endpoint;
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        MDC.clear();

        if (message instanceof TextMessage textMessage) {
            QesWebSocketMessage socketMessage = QesWebSocketMessage.create(textMessage.getPayload());

            MDC.put("marker", "WS");
            MDC.put("method", socketMessage.command().name());


            String dest = socketMessage.getHeader("destination", 0);

            if (dest == null) {
                String uri = Objects.requireNonNull(session.getUri()).toString();

                // '/'をつけるのはAPIやdestinationと同じようにするため
                dest = "/" + uri.substring(uri.indexOf(endpoint));
            }

            MDC.put("requestUri", dest);
            MDC.put("qesUserId", socketMessage.getHeader(headerUserId, 0));
            MDC.put("qesSpaceId", socketMessage.getHeader(headerSpaceId, 0));

            log.info("");

            switch (socketMessage.command()) {
                case CONNECT -> interceptor.connect(session, socketMessage);
                case DISCONNECT -> interceptor.disconnect(session, socketMessage);
            }
        }
        super.handleMessage(session, message);
        MDC.clear();
    }
}
