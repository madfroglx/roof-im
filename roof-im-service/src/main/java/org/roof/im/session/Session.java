package org.roof.im.session;

/**
 * 会话
 */
public class Session {
    /**
     * 会话ID
     */
    private String id;
    /**
     * 发起者
     */
    private String sender;
    /**
     * 接收者
     */
    private String receiver;
    /**
     * 开始时间
     */
    private long start;
    /**
     * 结束时间
     */
    private long end;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }
}
