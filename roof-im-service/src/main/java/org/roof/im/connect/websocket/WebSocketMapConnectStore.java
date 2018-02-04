package org.roof.im.connect.websocket;

import org.roof.im.connect.ConnectStore;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WebSocketMapConnectStore implements ConnectStore<WebSocketSession> {
    private Map<String, WebSocketSession> webSocketSessionMap = new ConcurrentHashMap<>();

    @Override
    public WebSocketSession get(String connectID) {
        return webSocketSessionMap.get(connectID);
    }

    @Override
    public void set(WebSocketSession connect) {
        webSocketSessionMap.put(connect.getId(), connect);
    }

    @Override
    public boolean remove(String connectID) {
        return !(webSocketSessionMap.remove(connectID) == null);
    }
}