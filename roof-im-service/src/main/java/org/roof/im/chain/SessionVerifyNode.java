package org.roof.im.chain;

import com.roof.chain.api.ValueStack;
import org.roof.im.request.MessageRequest;
import org.roof.im.session.Session;
import org.roof.im.session.SessionManager;

/**
 * session验证,验证会话双方的session是否存在
 */
public class SessionVerifyNode {
    /**
     * session不存在
     */
    private static final String SESSION_NOT_EXIST = "sessionNotExist";
    /**
     * session存在
     */
    private static final String SESSION_EXIST = "sessionExist";
    private SessionManager sessionManager;

    public String doNode(MessageRequest request, ValueStack valueStack) {
        String username = request.getUsername();
        String receiver = request.getReceiver();
        Session effective = sessionManager.queryEffective(username, receiver);
        if (effective == null) {
            return SESSION_NOT_EXIST;
        }
        valueStack.set(ImConstant.EFFECTIVE_SESSION, effective);
        return SESSION_EXIST;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }
}
