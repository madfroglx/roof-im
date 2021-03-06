package org.roof.im.chain;

import com.roof.chain.support.NodeResult;
import org.apache.commons.lang3.StringUtils;
import org.roof.im.chain.handler.SessionVo;
import org.roof.im.request.Request;
import org.roof.im.session.Session;
import org.roof.im.user.UserState;
import org.roof.im.user.UserStateService;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建返回客户端的sessionVo
 *
 * @author liuxin
 * @since 2018/3/4
 */
public class CreateSessionVoNode {
    /**
     * 没有未完成的会话
     */
    private static final String NO_INCOMPLETE_SESSION = "noIncompleteSession";
    /**
     * 查询未完成的会话成功
     */
    private static final String QUERY_INCOMPLETE_SESSION_SUCCESS = "queryIncompleteSessionSuccess";
    private UserStateService userStateService;

    public NodeResult<List<SessionVo>> doNode(Request request, List<Session> sessions) {
        //TODO session缓存
        if (sessions == null || sessions.size() == 0) {
            return new NodeResult<>(NO_INCOMPLETE_SESSION);
        }
        List<SessionVo> sessionVos = new ArrayList<>();
        String username = request.getUsername();
        for (Session session : sessions) {
            String receiver;
            if (StringUtils.equals(session.getReceiver(), username)) {
                receiver = session.getSender();
            } else {
                receiver = session.getReceiver();
            }
            String userState = ImConstant.OFFLINE;
            List<UserState> userStates = userStateService.getStates(receiver);
            if (userStates != null) {
                for (UserState state : userStates) {
                    if (StringUtils.equals(state.getState(), ImConstant.ONLINE)) {
                        userState = ImConstant.ONLINE;
                    }
                }
            }
            SessionVo sessionVo = new SessionVo();
            sessionVo.setSessionId(session.getId());
            sessionVo.setReceiver(receiver);
            sessionVo.setStartTime(session.getStartTime());
            sessionVo.setEndTime(session.getEndTime());
            sessionVo.setRealEndTime(session.getRealEndTime());
            sessionVo.setState(session.getState());
            sessionVo.setUserState(userState);
            sessionVos.add(sessionVo);
        }

        NodeResult<List<SessionVo>> result = new NodeResult<>(QUERY_INCOMPLETE_SESSION_SUCCESS);
        result.setData(sessionVos);
        return result;
    }

    public void setUserStateService(UserStateService userStateService) {
        this.userStateService = userStateService;
    }
}
