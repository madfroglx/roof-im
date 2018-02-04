package org.roof.im.handler.impl;

import org.roof.im.handler.AbstractRequestHandler;
import org.roof.im.request.MessageRequest;

public class MessageHandler extends AbstractRequestHandler<MessageRequest> {

    private static final String RECEIVER_NOT_EXISTS = "receiverNotExists";
    private static final String RECEIVER_OFFLINE = "receiverOffline";

    public String receive(MessageRequest request) {
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
