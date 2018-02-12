package org.roof.im.converter;

import com.alibaba.fastjson.JSONObject;
import org.roof.im.message.Message;
import org.roof.im.request.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class RequestTypeMappingRequestConverter implements RequestConverter {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestTypeMappingRequestConverter.class);
    private static Map<String, Class<? extends Request>> REQUEST_TYPE_MAPPING = new HashMap<>();

    static {
        REQUEST_TYPE_MAPPING.put(RequestType.online.name(), OnlineRequest.class);
        REQUEST_TYPE_MAPPING.put(RequestType.offline.name(), OfflineRequest.class);
        REQUEST_TYPE_MAPPING.put(RequestType.message.name(), MessageRequest.class);
    }


    @Override
    public boolean support(String requestType) {
        for (String type : REQUEST_TYPE_MAPPING.keySet()) {
            if (type.equalsIgnoreCase(requestType)) {
                return true;
            }
        }
        LOGGER.error("cannot found request type {} from mapping", requestType);
        return false;
    }

    @Override
    public Request toMessage(JSONObject jsonObjectMessage) {
        String requestType = jsonObjectMessage.getString("requestType");
        Class<? extends Request> cls = REQUEST_TYPE_MAPPING.get(requestType);
        if (cls == null) {
            LOGGER.error("cannot found request type {} from mapping", requestType);
            return null;
        }
        return jsonObjectMessage.toJavaObject(cls);
    }
}
