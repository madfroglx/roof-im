package org.roof.im.chain;

import com.roof.chain.api.ValueStack;
import com.roof.chain.support.NodeResult;
import org.roof.im.response.Response;

public class ReturnCodeResponseCreateNode {
    private String state = Response.SUCCESS;
    public static final String RESPONSE_CREATE_SUCCESS = "responseCreateSuccess";

    public String doNode(ValueStack valueStack) {
        NodeResult nodeResult = valueStack.getPreResult();
        Response response = new Response();
        response.setState(state);
        response.setMessage(nodeResult.getNext());
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
