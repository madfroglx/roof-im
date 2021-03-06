package org.roof.im.chain;

import com.roof.chain.api.ValueStack;
import com.roof.chain.support.NodeResult;
import org.apache.commons.lang3.StringUtils;
import org.roof.im.request.Request;
import org.roof.im.response.Response;

/**
 * 将节点的返回转化为服务端返回
 */
public class ReturnCodeResponseCreateNode {
    private String state;
    public static final String RESPONSE_CREATE_SUCCESS = "responseCreateSuccess";

    public String doNode(Request request, ValueStack valueStack) {
        NodeResult nodeResult = valueStack.getPreResult();
        Response<Object> response = new Response<>();
        response.setState(StringUtils.isBlank(state) ? nodeResult.getState() : state);
        response.setMessage(nodeResult.getNext());
        response.setResult(nodeResult.getData());
        response.setRequestType(valueStack.getAsString(ImConstant.REQUEST_TYPE));

        if (request != null) {
            response.setSeq(request.getSeq());
        }

        valueStack.set(ImConstant.RESPONSE, response);
        return RESPONSE_CREATE_SUCCESS;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
