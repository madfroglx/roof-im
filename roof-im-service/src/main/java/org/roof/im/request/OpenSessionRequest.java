package org.roof.im.request;

/**
 * 发起会话请求
 */
public class OpenSessionRequest extends Request {
    /**
     * 发起者
     */
    private String sender;
    /**
     * 接收者
     */
    private String receiver;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}