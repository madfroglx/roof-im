package org.roof.im.gateway.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.roof.chain.api.Chain;
import com.roof.chain.api.ValueStack;
import com.roof.chain.support.GenericValueStack;
import org.apache.commons.logging.Log;
import org.roof.im.chain.ImConstant;
import org.roof.im.connect.ConnectManager;
import org.roof.im.gateway.RequestEnterPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.ConcurrentWebSocketSessionDecorator;
import org.springframework.web.socket.handler.ExceptionWebSocketHandlerDecorator;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * 接受WebSocket连接,并接收客户端请求
 *
 * @author liuxin
 */
@Component
public class WebSocketRequestEnterPoint extends TextWebSocketHandler implements RequestEnterPoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketRequestEnterPoint.class);
    private ConnectManager<WebSocketSession> webSocketSessionConnectManager;
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
        valueStack.set(ImConstant.TEXT_MESSAGE, message);
        valueStack.set(ImConstant.CONNECT_ID, sessionID);
        JSONObject jsonObjectMessage;
        try {
            jsonObjectMessage = JSON.parseObject(message);
        } catch (Exception e) {
            LOGGER.error("input json error: {}", message);
            return;
        }
        valueStack.set(ImConstant.JSON_OBJECT_MESSAGE, jsonObjectMessage);
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
        webSocketSessionConnectManager.put(session.getId(), socketSessionDecorator);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        //TODO 关闭连接同时删除在线状态
        webSocketSessionConnectManager.remove(session.getId());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        LOGGER.error(exception.getMessage(), exception);
        WebSocketUtils.tryCloseWithError(session, exception, LOGGER);
    }

    public ConnectManager<WebSocketSession> getWebSocketSessionConnectManager() {
        return webSocketSessionConnectManager;
    }

    public void setWebSocketSessionConnectManager(ConnectManager<WebSocketSession> webSocketSessionConnectManager) {
        this.webSocketSessionConnectManager = webSocketSessionConnectManager;
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

    public Chain getEnterChain() {
        return enterChain;
    }

    @Autowired
    public void setEnterChain(@Qualifier("enterChain") Chain enterChain) {
        this.enterChain = enterChain;
    }

}
