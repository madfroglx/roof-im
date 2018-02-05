package org.roof.im.connect.websocket;

import org.roof.im.connect.ConnectStore;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WebSocketMapConnectStore implements ConnectStore<WebSocketSession> {
    private Map<String, WebSocketSession> webSocketSessionMap = new ConcurrentHashMap<>();

    @Override
    public WebSocketSession get(String connectId) {
        return webSocketSessionMap.get(connectId);
    }

    @Override
    public void put(String connectId, WebSocketSession connect) {
        webSocketSessionMap.put(connectId, connect);
    }

    @Override
    public boolean remove(String connectId) {
        return !(webSocketSessionMap.remove(connectId) == null);
    }
}