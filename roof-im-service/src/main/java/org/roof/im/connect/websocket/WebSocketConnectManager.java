package org.roof.im.connect.websocket;

import org.roof.im.connect.ConnectManager;
import org.roof.im.connect.ConnectStore;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

public class WebSocketConnectManager implements ConnectManager<WebSocketSession> {
    private ConnectStore<WebSocketSession> webSocketSessionConnectStore;

    @Override
    public WebSocketSession get(String connectId) {
        return webSocketSessionConnectStore.get(connectId);
    }

    @Override
    public void put(String connectId, WebSocketSession connect) {
        webSocketSessionConnectStore.put(connectId, connect);
    }

    @Override
    public boolean remove(String connectId) {
        return webSocketSessionConnectStore.remove(connectId);
    }

    @Override
    public WebSocketSession close(String connectId) throws IOException {
        WebSocketSession webSocketSession;
        try {
            webSocketSession = get(connectId);
            webSocketSession.close();
        } finally {
            webSocketSessionConnectStore.remove(connectId);
        }
        return webSocketSession;
    }

    public void setWebSocketSessionConnectStore(ConnectStore<WebSocketSession> webSocketSessionConnectStore) {
        this.webSocketSessionConnectStore = webSocketSessionConnectStore;
    }
}
