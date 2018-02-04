package org.roof.im.chain;

import org.roof.im.gateway.ResponseEndpoint;
import org.roof.im.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ResponseNode {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseNode.class);
    private static final String RESPONSE_FAIL = "responseFail";
    private static final String RESPONSE_SUCCESS = "responseSuccess";
    private ResponseEndpoint responseEndpoint;

    public String doNode(String connectID, Response response) {
        try {
            responseEndpoint.send(connectID, response);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            return RESPONSE_FAIL;
        }
        return RESPONSE_SUCCESS;
    }

    public void setResponseEndpoint(ResponseEndpoint responseEndpoint) {
        this.responseEndpoint = responseEndpoint;
    }
}
