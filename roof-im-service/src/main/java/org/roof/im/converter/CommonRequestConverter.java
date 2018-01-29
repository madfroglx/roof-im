package org.roof.im.converter;

import com.alibaba.fastjson.JSONObject;
import org.roof.im.request.MessageRequest;

public class CommonRequestConverter implements RequestConverter {

    @Override
    public boolean support(String messageType) {
        return true;
    }

    @Override
    public Object toMessage(JSONObject jsonObjectMessage) {
        return jsonObjectMessage.toJavaObject(MessageRequest.class);
    }
}
