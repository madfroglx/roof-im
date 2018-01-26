package org.roof.im.websocket;

import com.roof.chain.api.Chain;
import com.roof.chain.api.ValueStack;
import com.roof.chain.support.GenericValueStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.ConcurrentWebSocketSessionDecorator;
import org.springframework.web.socket.handler.TextWebSocketHandler;


public class WebSocketAcceptEndPoint extends TextWebSocketHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketAcceptEndPoint.class);
    private WebSocketSessionStore webSocketSessionStore;

    private int sendTimeLimit = 10 * 1000;
    private int sendBufferSizeLimit = 512 * 1024;
    private Chain enterChain;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        ValueStack valueStack = new GenericValueStack();
        valueStack.set("textMessage", message);
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
        webSocketSessionStore.set(socketSessionDecorator);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        webSocketSessionStore.remove(session.getId());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        LOGGER.error(exception.getMessage(), exception);
    }

    public void setWebSocketSessionStore(WebSocketSessionStore webSocketSessionStore) {
        this.webSocketSessionStore = webSocketSessionStore;
    }

    public WebSocketSessionStore getWebSocketSessionStore() {
        return webSocketSessionStore;
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
