package org.roof.im.chain.handler.impl;

import com.roof.chain.support.NodeResult;
import org.roof.im.request.HeartRequest;
import org.roof.im.session.Session;
import org.roof.im.session.SessionManager;
import org.roof.im.support.RemindTime;
import org.roof.im.user.UserStateService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理心跳请求
 *
 * @author liuxin
 * @since 2018/3/8
 */
public class HeartRequestHandlerNode {
    private UserStateService userStateService;
    private SessionManager sessionManager;
    private RemindTime remindTime;

    public NodeResult<Map> doNode(HeartRequest request) {
        NodeResult<Map> nodeResult = new NodeResult<>(NodeResult.SUCCESS);
        request.setConnectId(null);
        request.setServerName(null);
        Map<String, Object> data = new HashMap<>();
        data.put("requestPayload", request.getPayload());
        userStateService.getStates(request.getUsername());
        List<Session> sessions = sessionManager.queryIncomplete(request.getUsername());
        for (Session session : sessions) {
            session.setRemindTime(remindTime.getRemindTime(session));
        }
        data.put("sessions", sessions);
        nodeResult.setData(data);
        return nodeResult;
    }

    public void setUserStateService(UserStateService userStateService) {
        this.userStateService = userStateService;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public void setRemindTime(RemindTime remindTime) {
        this.remindTime = remindTime;
    }
}
