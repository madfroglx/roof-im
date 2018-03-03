package org.roof.im.chain;

import com.alibaba.fastjson.JSONObject;
import com.roof.chain.api.ValueStack;
import org.roof.im.message.Message;
import org.roof.im.transport.MessagePublisher;
import org.roof.im.user.UserState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class RequestMessagePublishNode {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestMessagePublishNode.class);
    private static final String PUBLISH_SUCCESS = "publishSuccess";
    private static final String PUBLISH_FAIL = "publishFail";
    private static final String PUBLISH_ERROR = "publishError";
    private MessagePublisher<Message> messagePublisher;
    private static final long DEFAULT_TIMEOUT = 1000;
    private long timeout = DEFAULT_TIMEOUT;

    public String doNode(Message message, List<UserState> userStates, ValueStack valueStack) {
        for (UserState userState : userStates) {
            boolean result = false;
            try {
                result = messagePublisher.publish(message, userState.getServerName(), timeout, TimeUnit.MILLISECONDS);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
            if (!result) {
                LOGGER.error("push message error: {} ,{}", userState.getUsername(), JSONObject.toJSONString(message));
            }
        }
        return PUBLISH_SUCCESS;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public void setMessagePublisher(MessagePublisher<Message> messagePublisher) {
        this.messagePublisher = messagePublisher;
    }
}
