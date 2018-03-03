package org.roof.im.converter;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.roof.im.request.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class RequestTypeMappingRequestConverter implements RequestConverter {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestTypeMappingRequestConverter.class);
    private static Map<String, Class<? extends Request>> REQUEST_TYPE_MAPPING = new HashMap<>();
    private RequestIdGenerator requestIdGenerator = new UUIDRequestIdGenerator();
    static {
        REQUEST_TYPE_MAPPING.put(RequestType.online.name(), UserStateRequest.class);
        REQUEST_TYPE_MAPPING.put(RequestType.hide.name(), UserStateRequest.class);
        REQUEST_TYPE_MAPPING.put(RequestType.offline.name(), UserStateRequest.class);
        REQUEST_TYPE_MAPPING.put(RequestType.openSession.name(), OpenSessionRequest.class);
        REQUEST_TYPE_MAPPING.put(RequestType.closeSession.name(), CloseSessionRequest.class);
        REQUEST_TYPE_MAPPING.put(RequestType.message.name(), MessageRequest.class);
        REQUEST_TYPE_MAPPING.put(RequestType.pullMessage.name(), PullMessageRequest.class);
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
        Request request = jsonObjectMessage.toJavaObject(cls);
        if (StringUtils.isBlank(request.getId())) {
            request.setId(requestIdGenerator.generateId(request));
        }
        return request;
    }

    public void setRequestIdGenerator(RequestIdGenerator requestIdGenerator) {
        this.requestIdGenerator = requestIdGenerator;
    }
}
