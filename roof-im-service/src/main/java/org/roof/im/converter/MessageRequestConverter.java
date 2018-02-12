package org.roof.im.converter;

import org.roof.im.request.RequestType;

public class MessageRequestConverter extends CommonRequestConverter {

    @Override
    public boolean support(String requestType) {
        return RequestType.message.name().equals(requestType);
    }
}
