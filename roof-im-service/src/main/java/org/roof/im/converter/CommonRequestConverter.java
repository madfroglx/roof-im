package org.roof.im.converter;

import com.alibaba.fastjson.JSONObject;
import org.roof.im.request.MessageRequest;
import org.roof.im.request.Request;

public class CommonRequestConverter implements RequestConverter<Request> {

    @Override
    public boolean support(String messageType) {
        return true;
    }

    @Override
    public Request toMessage(JSONObject jsonObjectMessage) {
        return jsonObjectMessage.toJavaObject(MessageRequest.class);
    }
}
