package org.roof.im.chain;

public class ReturnMessageExchangeNode {
    private String returnMessage = "success";

    public String doNode() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }
}
