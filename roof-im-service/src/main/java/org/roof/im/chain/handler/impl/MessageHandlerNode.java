package org.roof.im.chain.handler.impl;

import com.roof.chain.api.ValueStack;
import org.roof.im.chain.ImConstant;
import org.roof.im.chain.handler.AbstractRequestHandlerNode;
import org.roof.im.request.MessageRequest;
import org.roof.im.user.UserState;

import java.util.List;

/**
 * 消息处理
 */
public class MessageHandlerNode extends AbstractRequestHandlerNode<MessageRequest> {
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

    public String doNode(MessageRequest request, ValueStack valueStack) {
        String username = request.getUsername();
        if (!userService.exist(username)) {
            return USER_NOT_EXISTS;
        }
        String receiver = request.getReceiver();
        if (!userService.exist(receiver)) {
            return RECEIVER_NOT_EXISTS;
        }
        List<UserState> states = userStateService.getStates(receiver);
        if (states == null || states.size() == 0) {
            return RECEIVER_OFFLINE;
        }
        valueStack.set(ImConstant.USER_STATES, states);
        return MESSAGE_HANDLE_SUCCESS;
    }

}
