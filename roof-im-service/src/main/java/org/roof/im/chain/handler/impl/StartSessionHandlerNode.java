package org.roof.im.chain.handler.impl;

import com.roof.chain.api.ValueStack;
import org.apache.commons.lang3.StringUtils;
import org.roof.im.request.StartSessionRequest;
import org.roof.im.session.Session;
import org.roof.im.session.SessionManager;
import org.roof.im.session.jdbc.SessionDao;

/**
 * 开始session处理处理节点
 *
 * @author liuxin
 * @since 2018/3/15 0015
 */
public class StartSessionHandlerNode {
    /**
     * session不存在
     */
    private static final String SESSION_NOT_EXISTS = "sessionNotExists";
    /**
     * session已经关闭
     */
    private static final String SESSION_IS_CLOSED = "sessionIsClosed";
    /**
     * 开始时间大于结束时间
     */
    private static final String START_TIME_GT_END_TIME = "startTimeGtEndTime";
    /**
     * session开始成功
     */
    private static final String SESSION_START_SUCCESS = "sessionStartSuccess";
    private SessionManager sessionManager;
    private SessionDao sessionDao;

    public String doNode(StartSessionRequest request, ValueStack valueStack) {
        Long sessionId = request.getSessionId();
        Long startTime = request.getStartTime();
        Long endTime = request.getEndTime();
        Session session = sessionDao.load(sessionId);
        if (session == null) {
            return SESSION_NOT_EXISTS;
        }
        if (!StringUtils.equals(request.getUsername(), session.getReceiver())) {
            return SESSION_NOT_EXISTS;
        }
        if (session.getState() == 2) {
            return SESSION_IS_CLOSED;
        }
        if (session.getStartTime() == 0) {
            session.setStartTime(startTime);
        }
        session.setEndTime(endTime);
        if (session.getStartTime() > session.getEndTime()) {
            return START_TIME_GT_END_TIME;
        }
        sessionDao.updateEndTime(session.getId(), session.getEndTime());
        return SESSION_START_SUCCESS;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public void setSessionDao(SessionDao sessionDao) {
        this.sessionDao = sessionDao;
    }
}
