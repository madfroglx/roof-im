package org.roof.im.request;

/**
 * 关闭session请求
 */
public class CloseSessionRequest extends Request {

    private long sessionId;

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }
}
