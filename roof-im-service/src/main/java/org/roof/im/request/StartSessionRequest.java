package org.roof.im.request;

/**
 * 开始session请求
 *
 * @author liuxin
 * @since 2018/3/15 0015
 */
public class StartSessionRequest extends Request {
    private Long sessionId;
    private Long startTime;
    private Long endTime;

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
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
}
