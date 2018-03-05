package org.roof.im.chain.handler.impl;

import com.roof.chain.api.ValueStack;
import com.roof.chain.support.NodeResult;
import org.apache.commons.lang3.StringUtils;
import org.roof.im.message.Message;
import org.roof.im.request.Request;
import org.roof.im.user.UserState;
import org.roof.im.user.UserStateService;

import java.util.List;

/**
 * 连接不可用处理节点
 *
 * @author hzliuxin1
 * @since 2018/3/5 0005
 */
public class ConnectionAvailableHandlerNode {
    private UserStateService userStateService;

    public String doNode(String connectId, Message message, Request request, ValueStack valueStack) {
        String username = null;
        if (request != null && StringUtils.isNotBlank(request.getUsername())) {
            username = request.getUsername();
        }
        if (username == null && message != null && StringUtils.isNotBlank(message.getReceiver())) {
            username = message.getReceiver();
        }
        List<UserState> userStates = userStateService.getStates(username);
        if (userStates == null) {
            return NodeResult.SUCCESS;
        }
        userStates.removeIf(userState -> StringUtils.equals(userState.getConnectId(), connectId));
        if (userStates.size() == 0) {
            userStateService.offline(username);
        } else {
            userStateService.online(username, userStates);
        }
        return NodeResult.SUCCESS;
    }

    public void setUserStateService(UserStateService userStateService) {
        this.userStateService = userStateService;
    }
}
