package org.roof.im.request;

/**
 * 获取未收取消息
 */
public class PullNotReceivedMessageRequest extends Request {
    private String sender;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
