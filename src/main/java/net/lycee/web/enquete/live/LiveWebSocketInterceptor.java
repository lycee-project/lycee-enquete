package net.lycee.web.enquete.live;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.socket.WebSocketSession;

import java.util.Objects;

@Slf4j
public class LiveWebSocketInterceptor implements QesWebSocketInterceptor {
    private final String endpoint;

    @Autowired
    private SimpMessageSendingOperations messageTemplate;


    public LiveWebSocketInterceptor(
            String endpoint
    ) {
        this.endpoint = endpoint;
    }

    @Override
    public void connect(WebSocketSession session, QesWebSocketMessage message) {
        log.info("[connect]: {}", session.getUri());

        String[] paths = Objects.requireNonNull(session.getUri()).toString().split("/");
        String roomId = paths[paths.length - 1];
        if (roomId.isEmpty() || roomId.equals(endpoint)) {
            return;
        }

        String userId = message.getHeader("X-LYCEE-USER-ID", 0);
        String spaceId = message.getHeader("X-LYCEE-SPACE-ID", 0);

        log.info("userId: {}, spaceId: {}", userId, spaceId);
    }

    @Override
    public void disconnect(WebSocketSession session, QesWebSocketMessage message) {
        log.info("[disconnect]: {}", session.getUri());

        String[] paths = Objects.requireNonNull(session.getUri()).toString().split("/");
        String roomId = paths[paths.length - 1];
        if (roomId.isEmpty() || roomId.equals(endpoint)) {
            return;
        }

        String userId = message.getHeader("X-LYCEE-USER-ID", 0);
        String spaceId = message.getHeader("X-LYCEE-SPACE-ID", 0);

        log.info("userId: {}, spaceId: {}", userId, spaceId);
    }
}
