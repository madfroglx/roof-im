package org.roof.im.gateway.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.roof.chain.api.Chain;
import com.roof.chain.api.ValueStack;
import com.roof.chain.support.GenericValueStack;
import org.roof.im.chain.ImValueStackConstant;
import org.roof.im.gateway.RequestEnterPoint;
import org.roof.im.connect.ConnectStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.ConcurrentWebSocketSessionDecorator;
import org.springframework.web.socket.handler.TextWebSocketHandler;


public class WebSocketRequestEnterPoint extends TextWebSocketHandler implements RequestEnterPoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketRequestEnterPoint.class);
    private ConnectStore webSocketConnectStore;

    private int sendTimeLimit = 10 * 1000;
    private int sendBufferSizeLimit = 512 * 1024;
    private Chain enterChain;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        String textMessage = message.getPayload();
        String sessionID = session.getId();
        receive(sessionID, textMessage);
    }

    @Override
    public void receive(String sessionID, String message) {
        ValueStack valueStack = new GenericValueStack();
        valueStack.set(ImValueStackConstant.TEXT_MESSAGE, message);
        valueStack.set(ImValueStackConstant.SESSION_ID, sessionID);
        JSONObject jsonObjectMessage = JSON.parseObject(message);
        valueStack.set(ImValueStackConstant.JSON_OBJECT_MESSAGE, jsonObjectMessage);
        try {
            enterChain.doChain(valueStack);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        ConcurrentWebSocketSessionDecorator socketSessionDecorator
                = new ConcurrentWebSocketSessionDecorator(session, getSendTimeLimit(), getSendBufferSizeLimit());
        webSocketConnectStore.set(socketSessionDecorator);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        webSocketConnectStore.remove(session.getId());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        LOGGER.error(exception.getMessage(), exception);
    }

    public void setWebSocketConnectStore(ConnectStore webSocketConnectStore) {
        this.webSocketConnectStore = webSocketConnectStore;
    }

    public ConnectStore getWebSocketConnectStore() {
        return webSocketConnectStore;
    }

    public int getSendTimeLimit() {
        return sendTimeLimit;
    }

    public void setSendTimeLimit(int sendTimeLimit) {
        this.sendTimeLimit = sendTimeLimit;
    }

    public int getSendBufferSizeLimit() {
        return sendBufferSizeLimit;
    }

    public void setSendBufferSizeLimit(int sendBufferSizeLimit) {
        this.sendBufferSizeLimit = sendBufferSizeLimit;
    }


}
