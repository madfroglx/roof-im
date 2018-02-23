package org.roof.im.message;

import org.roof.im.request.MessageRequest;

public class MessageUtils {
    public static Message request2Message(MessageRequest messageRequest) {
        Message message = new Message();
        message.setPayload(messageRequest.getPayload());
        message.setReceiver(messageRequest.getReceiver());
        message.setSender(messageRequest.getUsername());
        message.setCreateTime(messageRequest.getCreateTime());
//     TODO   message.setState();
        message.setType(messageRequest.getType().name());
        return message;
    }
}
