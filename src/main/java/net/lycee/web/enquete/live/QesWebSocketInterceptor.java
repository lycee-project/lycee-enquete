package net.lycee.web.enquete.live;

import org.springframework.web.socket.WebSocketSession;

public interface QesWebSocketInterceptor {

    void connect(WebSocketSession session,QesWebSocketMessage message);

    void disconnect(WebSocketSession session,QesWebSocketMessage message);

}
