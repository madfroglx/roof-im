package org.roof.im.chain;

import com.alibaba.fastjson.JSONObject;
import com.roof.chain.api.ValueStack;
import org.roof.im.converter.RequestConverter;
import org.roof.im.converter.RequestTypeMappingRequestConverter;
import org.roof.im.request.Request;
import org.roof.im.transport.ServerNameBuilder;
import org.springframework.util.Assert;

import java.util.List;

/**
 * 请求反序列化
 *
 * @author liuxin
 */
public class RequestConverterNode {
    private static final RequestConverter DEFAULT_CONVERTER = new RequestTypeMappingRequestConverter();
    private RequestConverter commonRequestConverter = DEFAULT_CONVERTER;
    private List<RequestConverter> requestConverters = null;
    private ServerNameBuilder serverNameBuilder;

    private static final String REQUEST_CONVERT_ERROR = "requestConvertError";
    private static final String REQUEST_CONVERT_SUCCESS = "requestConvertSuccess";

    public String doNode(JSONObject jsonObjectMessage, String currentUser, String connectId, ValueStack valueStack) {
        Assert.notNull(jsonObjectMessage, "property jsonObjectMessage cannot null");
        String requestType = jsonObjectMessage.getString("requestType");
        Request request = null;
        if (requestConverters != null) {
            for (RequestConverter converter : requestConverters) {
                if (converter.support(requestType)) {
                    request = converter.toMessage(jsonObjectMessage);
                    break;
                }
            }
        }

        if (request == null) {
            request = commonRequestConverter.toMessage(jsonObjectMessage);
        }
        if (request == null) {
            return REQUEST_CONVERT_ERROR;
        }
        valueStack.set(ImConstant.REQUEST_TYPE, request.getRequestType());
        request.setUsername(currentUser);
        request.setConnectId(connectId);
        request.setServerName(serverNameBuilder.getName());
        request.setCreateTime(System.currentTimeMillis());
        valueStack.set(ImConstant.REQUEST, request);
        return REQUEST_CONVERT_SUCCESS;
    }

    public void setCommonRequestConverter(RequestConverter commonRequestConverter) {
        this.commonRequestConverter = commonRequestConverter;
    }

    public void setRequestConverters(List<RequestConverter> requestConverters) {
        this.requestConverters = requestConverters;
    }

    public RequestConverter getCommonRequestConverter() {
        return commonRequestConverter;
    }


    public List<RequestConverter> getRequestConverters() {
        return requestConverters;
    }

    public void setServerNameBuilder(ServerNameBuilder serverNameBuilder) {
        this.serverNameBuilder = serverNameBuilder;
    }
}
