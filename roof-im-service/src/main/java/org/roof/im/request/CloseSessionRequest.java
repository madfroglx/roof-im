package org.roof.im.request;

/**
 * 关闭session请求
 */
public class CloseSessionRequest extends Request {

    private String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
