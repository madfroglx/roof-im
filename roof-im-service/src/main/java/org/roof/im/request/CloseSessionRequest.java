package org.roof.im.request;

import javax.validation.constraints.NotEmpty;

/**
 * 关闭session请求
 */
public class CloseSessionRequest extends Request {
    @NotEmpty
    private long sessionId;

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }
}
