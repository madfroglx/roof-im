package org.roof.im.chain.handler.impl;

import com.roof.chain.api.ValueStack;
import org.roof.im.chain.handler.AbstractRequestHandlerNode;
import org.roof.im.request.MessageRequest;

public class MessageHandlerNode extends AbstractRequestHandlerNode<MessageRequest> {

    private static final String RECEIVER_NOT_EXISTS = "receiverNotExists";
    private static final String RECEIVER_OFFLINE = "receiverOffline";

    public String doNode(MessageRequest request, ValueStack valueStack) {
        String username = request.getUsername();
        if (!userService.exist(username)) {
            return USER_NOT_EXISTS;
        }
        String receiver = request.getReceiver();
        if (!userService.exist(receiver)) {
            return RECEIVER_NOT_EXISTS;
        }
//        UserState userState = userStateService.getStatus(receiver);
//        if (userState == null) {
//            return RECEIVER_OFFLINE;
//        }

        return null;
    }

}
