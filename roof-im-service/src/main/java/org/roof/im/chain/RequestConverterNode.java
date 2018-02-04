package org.roof.im.chain;

import com.alibaba.fastjson.JSONObject;
import com.roof.chain.api.ValueStack;
import org.roof.im.converter.CommonRequestConverter;
import org.roof.im.converter.RequestConverter;
import org.roof.im.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.List;

/**
 * 请求反序列化
 *
 * @author liuxin
 */
public class RequestConverterNode {
    private static final RequestConverter DEFAULT_CONVERTER = new CommonRequestConverter();
    private RequestConverter commonRequestConverter = DEFAULT_CONVERTER;
    private List<RequestConverter> requestConverters;

    private static final String REQUEST_CONVERT_ERROR = "requestConvertError";
    private static final String REQUEST_CONVERT_SUCCESS = "requestConvertSuccess";

    public String doNode(JSONObject jsonObjectMessage, String currentUser, ValueStack valueStack) {
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
        valueStack.set(ImConstant.REQUEST, request);
        return REQUEST_CONVERT_SUCCESS;
    }

    public void setCommonRequestConverter(RequestConverter commonRequestConverter) {
        this.commonRequestConverter = commonRequestConverter;
    }

    @Autowired
    public void setRequestConverters(List<RequestConverter> requestConverters) {
        this.requestConverters = requestConverters;
    }

    public RequestConverter getCommonRequestConverter() {
        return commonRequestConverter;
    }


    public List<RequestConverter> getRequestConverters() {
        return requestConverters;
    }


}
