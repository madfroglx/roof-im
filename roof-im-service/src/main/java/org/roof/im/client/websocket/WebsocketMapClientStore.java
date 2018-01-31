package org.roof.im.client.websocket;

import org.roof.im.client.ClientStore;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WebsocketMapClientStore implements ClientStore<WebSocketSession> {
    private Map<String, WebSocketSession> webSocketSessionMap = new ConcurrentHashMap<>();

    @Override
    public WebSocketSession get(String sessionId) {
        return webSocketSessionMap.get(sessionId);
    }

    @Override
    public void set(WebSocketSession webSocketSession) {
        webSocketSessionMap.put(webSocketSession.getId(), webSocketSession);
    }

    @Override
    public boolean remove(String sessionId) {
        return !(webSocketSessionMap.remove(sessionId) == null);
    }
}