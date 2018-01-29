package org.roof.im.request;

/**
 * 用户发送消息
 */
public class MessageRequest extends Request {
    private String receiver;//接收人
    private RequestContentType type; //消息类型
    private String payload; //消息体

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public RequestContentType getType() {
        return type;
    }

    public void setType(RequestContentType type) {
        this.type = type;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}