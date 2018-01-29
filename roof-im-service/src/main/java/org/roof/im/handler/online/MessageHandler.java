package org.roof.im.handler.online;

import org.roof.im.handler.AbstractRequestHandler;
import org.roof.im.request.MessageRequest;
import org.roof.im.user.UserStatus;

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
        UserStatus userStatus = userStatusService.getStatus(receiver);
        if (userStatus == null) {
            return RECEIVER_OFFLINE;
        }

        return null;
    }

}
