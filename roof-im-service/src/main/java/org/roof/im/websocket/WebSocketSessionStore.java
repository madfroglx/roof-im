package org.roof.im.websocket;

import org.springframework.web.socket.WebSocketSession;

public interface WebSocketSessionStore {

    WebSocketSession get(String sessionId);

    void set(WebSocketSession webSocketSession);

    boolean remove(String sessionId);
}
