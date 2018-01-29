package org.roof.im.response;

import com.roof.chain.api.ValueStack;
import com.roof.chain.support.NodeResult;
import org.roof.im.chain.ChainConstant;
import org.roof.im.chain.ImValueStackConstant;

public class ReturnMessageResponseCreateNode {
    private String state = Response.SUCCESS;
    public static final String RESPONSE_CREATE_SUCCESS = "responseCreateSuccess";

    public String doNode(ValueStack valueStack) {
        NodeResult nodeResult = valueStack.getPreResult();
        Response response = new Response();
        response.setState(state);
        response.setMessage(nodeResult.getNext());
        valueStack.set(ImValueStackConstant.RESPONSE, response);
        return RESPONSE_CREATE_SUCCESS;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
