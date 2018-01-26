package org.roof.im.message;

/**
 * 用户发送消息
 */
public class MessagePayload {
    private String form; //发送人
    private String to;//接收人
    private MessageType type; //消息类型
    private String payload; //消息体

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}