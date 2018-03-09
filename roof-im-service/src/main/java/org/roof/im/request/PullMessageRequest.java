package org.roof.im.request;

import javax.validation.constraints.NotEmpty;

/**
 * 拉取消息请求
 */
public class PullMessageRequest extends Request {
    @NotEmpty
    private String sender; //消息发送者
    private Long startTime; //查询开始时间
    private Long endTime;//查询结束时间
    private Integer state; //状态
    private Integer offset;//偏移量

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
