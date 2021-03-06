package org.roof.im.chain.handler.impl;

import com.roof.chain.api.ValueStack;
import com.roof.chain.support.NodeResult;
import org.apache.commons.lang3.StringUtils;
import org.roof.im.chain.ImConstant;
import org.roof.im.message.Message;
import org.roof.im.transport.ServerNameBuilder;
import org.roof.im.user.UserState;
import org.roof.im.user.UserStateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 将消息发送给客户端
 */
public class MessageRequestHandlerNode {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageRequestHandlerNode.class);
    /**
     * 客户端连接未找到
     */
    private static final String CANNOT_FOUND_CONNECT = "cannotFoundConnect";
    /**
     * 消息请求转换成功
     */
    private static final String MESSAGE_REQUEST_TRANSFORM_SUCCESS = "messageRequestTransformSuccess";
    /**
     * 不支持的请求类型
     */
    private static final String NONSUPPORT_REQUEST_TYPE = "nonsupportRequestType";
    private UserStateService userStateService;
    private ServerNameBuilder serverNameBuilder;


    public NodeResult<Message> doNode(Message message, ValueStack valueStack) {
//        MessageRequest messageRequest;
//        if (request instanceof MessageRequest) {
//            messageRequest = (MessageRequest) request;
//        } else {
//            LOGGER.error("request error type : {}, mast be {}",
//                    request.getClass().getSimpleName(), MessageRequest.class.getSimpleName());
//            return new NodeResult<>(NONSUPPORT_REQUEST_TYPE);
//        }
        String receiver = message.getReceiver();
        List<UserState> userStates = userStateService.getStates(receiver);
        String connectId = null;
        for (UserState userState : userStates) {
            if (StringUtils.equals(userState.getServerName(), serverNameBuilder.getName())
                    && StringUtils.equals(userState.getClientType(), message.getClientType())) {
                connectId = userState.getConnectId();
            }
        }
        if (connectId == null) {
            return new NodeResult(CANNOT_FOUND_CONNECT);
        }
        valueStack.set(ImConstant.CONNECT_ID, connectId);
        NodeResult result = new NodeResult(MESSAGE_REQUEST_TRANSFORM_SUCCESS);
        result.setData(message);
        valueStack.set(ImConstant.REQUEST_TYPE, ImConstant.MESSAGE);
        return result;
    }

    public void setUserStateService(UserStateService userStateService) {
        this.userStateService = userStateService;
    }

    public void setServerNameBuilder(ServerNameBuilder serverNameBuilder) {
        this.serverNameBuilder = serverNameBuilder;
    }
}
