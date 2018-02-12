package org.roof.im.converter;

import com.alibaba.fastjson.JSONObject;
import org.roof.im.request.OnlineRequest;
import org.roof.im.request.Request;
import org.roof.im.request.RequestType;

public class OnlineRequestConverter extends CommonRequestConverter {

    @Override
    public boolean support(String requestType) {
        return RequestType.online.name().equals(requestType);
    }

    @Override
    public Request toMessage(JSONObject jsonObjectMessage) {
        return jsonObjectMessage.toJavaObject(OnlineRequest.class);
    }
}
