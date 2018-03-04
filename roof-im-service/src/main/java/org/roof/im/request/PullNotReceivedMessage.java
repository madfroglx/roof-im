package org.roof.im.request;

/**
 * 拉取未收取消息
 */
public class PullNotReceivedMessage extends Request {
    private String sender;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
