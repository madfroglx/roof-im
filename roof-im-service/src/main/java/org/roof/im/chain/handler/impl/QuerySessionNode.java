package org.roof.im.chain.handler.impl;

import com.roof.chain.support.NodeResult;
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
public class QuerySessionNode {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuerySessionNode.class);
    /**
     * 查询session失败
     */
    private static final String QUERY_SESSION_ERROR = "querySessionError";
    /**
     * 查询session成功
     */
    private static final String QUERY_SESSION_SUCCESS = "querySessionSuccess";

    private SessionManager sessionManager;

    public NodeResult<List<Session>> doNode(QuerySessionRequest request) {
        List<Session> sessions;
        try {
            sessions = sessionManager.queryAllEffective(request.getUsername());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return new NodeResult<>(QUERY_SESSION_ERROR);
        }
        NodeResult<List<Session>> result = new NodeResult<>(QUERY_SESSION_SUCCESS);
        result.setData(sessions);
        return result;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }
}
