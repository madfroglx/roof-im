package org.roof.im.request;

/**
 * 用户发送消息
 */
public class MessageRequest extends Request {
    private String receiver;//接收人
    private ContentType type; //消息类型
    private String payload; //消息体
    private String seq; //序列

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

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }
}