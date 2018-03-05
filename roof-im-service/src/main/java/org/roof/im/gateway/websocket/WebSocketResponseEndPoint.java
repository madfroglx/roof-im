package org.roof.im.gateway.websocket;

import com.alibaba.fastjson.JSON;
import org.roof.im.connect.ConnectManager;
import org.roof.im.gateway.ConnectNotExistException;
import org.roof.im.gateway.ResponseEndpoint;
import org.roof.im.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

@Component
public class WebSocketResponseEndPoint implements ResponseEndpoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketResponseEndPoint.class);
    private ConnectManager<WebSocketSession> webSocketSessionConnectManager;
    @Override
    public void send(String connectId, Response response) throws IOException, ConnectNotExistException {
        WebSocketSession webSocketSession = webSocketSessionConnectManager.get(connectId);
        if (webSocketSession == null) {
            throw new ConnectNotExistException();
        }
//        if (!webSocketSession.isOpen()) {
//            return;
//        }
        TextMessage textMessage = new TextMessage(JSON.toJSONString(response));
        try {
            webSocketSession.sendMessage(textMessage);
        } catch (Exception e) {
            //消息推送异常则关闭连接
            WebSocketUtils.tryCloseWithError(webSocketSession, e, LOGGER);
            throw e;
        }
    }

    public ConnectManager<WebSocketSession> getWebSocketSessionConnectManager() {
        return webSocketSessionConnectManager;
    }

    @Autowired
    public void setWebSocketSessionConnectManager(ConnectManager<WebSocketSession> webSocketSessionConnectManager) {
        this.webSocketSessionConnectManager = webSocketSessionConnectManager;
    }
}
