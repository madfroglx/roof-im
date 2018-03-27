package org.roof.im.chain.handler.impl;

import com.roof.chain.api.ValueStack;
import com.roof.chain.support.NodeResult;
import org.roof.im.chain.ImConstant;
import org.roof.im.chain.handler.AbstractRequestHandlerNode;
import org.roof.im.message.Message;
import org.roof.im.request.MessageRequest;
import org.roof.im.user.UserService;
import org.roof.im.user.UserState;
import org.roof.im.user.UserStateService;

import java.util.List;

/**
 * 消息处理
 */
public class MessageHandlerNode {
    /**
     * 消息接受者不存在
     */
    private static final String RECEIVER_NOT_EXISTS = "receiverNotExists";
    /**
     * 消息消息接收者下线
     */
    private static final String RECEIVER_OFFLINE = "receiverOffline";
    /**
     * 消息处理成功
     */
    private static final String MESSAGE_HANDLE_SUCCESS = "messageHandleSuccess";

    private UserService userService;
    private UserStateService userStateService;

    public NodeResult doNode(MessageRequest request, Message message, ValueStack valueStack) {
        String receiver = request.getReceiver();
        if (!userService.exist(receiver)) {
            return new NodeResult(RECEIVER_NOT_EXISTS);
        }
        List<UserState> states = userStateService.getStates(receiver);
        if (states == null || states.size() == 0) {
            NodeResult r = new NodeResult(RECEIVER_OFFLINE);
            r.setData(message);
            return r;
        }
        valueStack.set(ImConstant.USER_STATES, states);
        return new NodeResult(MESSAGE_HANDLE_SUCCESS);
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setUserStateService(UserStateService userStateService) {
        this.userStateService = userStateService;
    }
}
