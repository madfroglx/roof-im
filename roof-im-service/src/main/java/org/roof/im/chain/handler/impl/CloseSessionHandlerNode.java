package org.roof.im.chain.handler.impl;

import com.roof.chain.api.ValueStack;
import org.roof.im.chain.handler.AbstractRequestHandlerNode;
import org.roof.im.request.CloseSessionRequest;
import org.roof.im.session.Session;
import org.roof.im.session.SessionManager;

/**
 * 结束session
 */
public class CloseSessionHandlerNode extends AbstractRequestHandlerNode<CloseSessionRequest> {
    /**
     * session 不存在
     */
    private static final String SESSION_NOT_EXIST = "sessionNotExist";
    /**
     * session 关闭成功
     */
    private static final String SESSION_CLOSE_SUCCESS = "sessionCloseSuccess";
    private SessionManager sessionManager;

    @Override
    public Object doNode(CloseSessionRequest request, ValueStack valueStack) {
        long sessionId = request.getSessionId();
        if (sessionManager.close(sessionId)) {
            return SESSION_CLOSE_SUCCESS;

        }
        return SESSION_NOT_EXIST;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }
}

