package org.roof.im.converter;

import com.alibaba.fastjson.JSONObject;
import com.roof.chain.api.ValueStack;
import org.roof.im.chain.ChainConstant;
import org.roof.im.request.Request;
import org.springframework.util.Assert;

import java.util.List;

public class RequestConverterNode {
    private static final RequestConverter DEFAULT_CONVERTER = new CommonRequestConverter();
    private RequestConverter defaultConverter = DEFAULT_CONVERTER;
    private List<RequestConverter> converters;

    private static final String REQUEST_CONVERT_ERROR = "requestConvertError";
    private static final String REQUEST_CONVERT_SUCCESS = "requestConvertSuccess";

    public String doNode(JSONObject jsonObjectMessage, ValueStack valueStack) {
        Assert.notNull(jsonObjectMessage, "property jsonObjectMessage cannot null");
        String requestType = jsonObjectMessage.getString("requestType");
        Request request = null;
        if (converters != null) {
            for (RequestConverter converter : converters) {
                if (converter.support(requestType)) {
                    request = converter.toMessage(jsonObjectMessage);
                    break;
                }
            }
        }
        if (request == null) {
            request = defaultConverter.toMessage(jsonObjectMessage);
        }
        if (request == null) {
            return REQUEST_CONVERT_ERROR;
        }
        valueStack.set("request", request);
        return REQUEST_CONVERT_SUCCESS;
    }

    public RequestConverter getDefaultConverter() {
        return defaultConverter;
    }

    public void setDefaultConverter(RequestConverter defaultConverter) {
        this.defaultConverter = defaultConverter;
    }

    public List<RequestConverter> getConverters() {
        return converters;
    }

    public void setConverters(List<RequestConverter> converters) {
        this.converters = converters;
    }
}
