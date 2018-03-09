package org.roof.im.request;

import javax.validation.constraints.NotEmpty;

/**
 * 发起会话请求
 */
public class OpenSessionRequest extends Request {
    /**
     * 发起者
     */
    @NotEmpty
    private String sender;
    /**
     * 接收者
     */
    @NotEmpty
    private String receiver;
    /**
     * 开始时间
     */
    private long startTime;
    /**
     * 结束时间
     */
    private long endTime;

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

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}