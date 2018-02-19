package org.roof.im.chain.handler.impl;

import com.roof.chain.api.ValueStack;
import org.apache.commons.lang3.StringUtils;
import org.roof.im.chain.handler.AbstractRequestHandlerNode;
import org.roof.im.request.UserStateRequest;
import org.roof.im.transport.ServerNameBuilder;
import org.roof.im.user.UserService;
import org.roof.im.user.UserState;
import org.roof.im.user.UserStateService;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户状态变更
 */
public class UserStateHandlerNode extends AbstractRequestHandlerNode<UserStateRequest> {
    /**
     * 用户状态变更成功
     */
    private static final String USER_STATE_CHANGE_SUCCESS = "userStateChangeSuccess";

    private UserStateService userStateService;

    private UserService userService;

    private ServerNameBuilder serverNameBuilder;

    public String doNode(UserStateRequest userStateRequest, ValueStack valueStack) {
        String username = userStateRequest.getUsername();
        List<UserState> userStates = userStateService.getStates(username);
        List<UserState> newUserStates = new ArrayList<>();
        UserState userState = createUserState(userStateRequest);
        newUserStates.add(userState);
        if (userStates != null && userStates.size() > 0) {
            for (UserState us : userStates) {
                if (!StringUtils.equals(userState.getClientType(), us.getClientType())) {
                    newUserStates.add(us);
                }
            }
        }
        userStateService.online(username, newUserStates);
        return USER_STATE_CHANGE_SUCCESS;
    }

    private UserState createUserState(UserStateRequest userStateRequest) {
        String nodeName = serverNameBuilder.getName();
        UserState userState = new UserState();
        userState.setUsername(userStateRequest.getUsername());
        userState.setConnectId(userStateRequest.getConnectId());
        userState.setClientType(userStateRequest.getClientType());
        userState.setServerName(nodeName);
        userState.setUpdateTime(System.currentTimeMillis());
        userState.setState(userStateRequest.getRequestType());
        return userState;
    }

    public UserStateService getUserStateService() {
        return userStateService;
    }

    public void setUserStateService(UserStateService userStateService) {
        this.userStateService = userStateService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setServerNameBuilder(ServerNameBuilder serverNameBuilder) {
        this.serverNameBuilder = serverNameBuilder;
    }
}
