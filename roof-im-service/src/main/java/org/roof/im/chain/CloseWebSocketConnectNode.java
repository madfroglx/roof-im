package org.roof.im.chain;

import org.roof.im.connect.ConnectManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class CloseWebSocketConnectNode {
    private static final Logger LOGGER = LoggerFactory.getLogger(CloseWebSocketConnectNode.class);
    private ConnectManager connectManager;
    private static final String CLOSE_SUCCESS = "closeSuccess";
    private static final String CLOSE_ERROR = "closeError";

    private String doNode(String connectId) {
        try {
            connectManager.close(connectId);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            return CLOSE_ERROR;
        }
        return CLOSE_SUCCESS;
    }

    public void setConnectManager(ConnectManager connectManager) {
        this.connectManager = connectManager;
    }
}
