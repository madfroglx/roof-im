package org.roof.im.converter;

import org.roof.im.request.RequestType;

public class OnlineRequestRequestConverter extends CommonRequestConverter {

    @Override
    public boolean support(String messageType) {
        return RequestType.online.name().equals(messageType);
    }
}
