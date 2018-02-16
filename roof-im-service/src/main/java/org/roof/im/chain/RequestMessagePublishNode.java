package org.roof.im.chain;

import com.roof.chain.api.ValueStack;
import org.roof.im.request.Request;
import org.roof.im.transport.RequestPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class RequestMessagePublishNode {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestMessagePublishNode.class);
    private static final String PUBLISH_SUCCESS = "publishSuccess";
    private static final String PUBLISH_FAIL = "publishFail";
    private static final String PUBLISH_ERROR = "publishError";
    private RequestPublisher<Request> requestPublisher;
    private static final long DEFAULT_TIMEOUT = 1000;
    private long timeout = DEFAULT_TIMEOUT;

    public String doNode(Request request, ValueStack valueStack) {
        try {
            boolean result = requestPublisher.publish(request, timeout, TimeUnit.MILLISECONDS);
            if (result) {
                return PUBLISH_SUCCESS;
            } else {
                return PUBLISH_FAIL;
            }
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
            return PUBLISH_ERROR;
        }
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public void setRequestPublisher(RequestPublisher<Request> requestPublisher) {
        this.requestPublisher = requestPublisher;
    }
}
