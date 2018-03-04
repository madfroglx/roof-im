package org.roof.im.chain;

import com.roof.chain.api.ValueStack;
import com.roof.chain.support.NodeResult;

public class ReturnMessageExchangeNode {
    private String returnMessage = "success";

    public NodeResult doNode(ValueStack valueStack) {
        NodeResult result = valueStack.getPreResult();
        result.setNext(returnMessage);
        return result;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }
}
