package org.roof.im.request;

import javax.validation.constraints.NotEmpty;

/**
 * 用户发送消息
 */
public class MessageRequest extends Request {
    @NotEmpty
    private String receiver;//接收人
    @NotEmpty
    private ContentType type; //消息类型
    @NotEmpty
    private String payload; //消息体

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public ContentType getType() {
        return type;
    }

    public void setType(ContentType type) {
        this.type = type;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}