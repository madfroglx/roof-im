package org.roof.im.converter;

import org.roof.im.request.RequestType;

public class MessageRequestRequestConverter extends CommonRequestConverter {

    @Override
    public boolean support(String messageType) {
        return RequestType.message.name().equals(messageType);
    }
}
