package org.roof.im.chain.handler;

/**
 * 会话VO
 *
 * @author liuxin
 * @date 2018/3/4
 */
public class SessionVo {
    private long sessionId; //会话id
    private String receiver; //会话接收方
    private String userState; //用户状态 online, offline
    private long startTime; //开始时间
    private long endTime; //结束时间
    private long realEndTime; //实际结束时间
    /**
     * 状态(0:创建,2:结束)
     */
    private int state; //会话状态

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
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

    public long getRealEndTime() {
        return realEndTime;
    }

    public void setRealEndTime(long realEndTime) {
        this.realEndTime = realEndTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
