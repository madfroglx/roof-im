package org.roof.im.chain.handler.impl;

import com.roof.chain.api.ValueStack;
import org.roof.im.chain.ImConstant;
import org.roof.im.request.QuerySessionRequest;
import org.roof.im.session.Session;
import org.roof.im.session.SessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 查询session
 *
 * @author liuxin
 * @date 2018/3/4
 */
public class QuerySessionHandleNode {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuerySessionHandleNode.class);
    /**
     * 查询session失败
     */
    private static final String QUERY_SESSION_ERROR = "querySessionError";
    /**
     * 查询session成功
     */
    private static final String QUERY_SESSION_SUCCESS = "querySessionSuccess";

    private SessionManager sessionManager;

    public String doNode(QuerySessionRequest request, ValueStack valueStack) {
        List<Session> sessions;
        try {
            sessions = sessionManager.queryIncomplete(request.getUsername());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return QUERY_SESSION_ERROR;
        }
        valueStack.set(ImConstant.SESSIONS, sessions);
        return QUERY_SESSION_SUCCESS;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }
}
