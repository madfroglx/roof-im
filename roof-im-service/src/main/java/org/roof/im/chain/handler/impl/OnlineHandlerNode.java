package org.roof.im.chain.handler.impl;

import com.roof.chain.api.ValueStack;
import org.apache.commons.lang3.StringUtils;
import org.roof.im.chain.handler.AbstractRequestHandlerNode;
import org.roof.im.request.OnlineRequest;
import org.roof.im.transport.ServerNameBuilder;
import org.roof.im.user.UserService;
import org.roof.im.user.UserState;
import org.roof.im.user.UserStateService;

import java.util.ArrayList;
import java.util.List;

public class OnlineHandlerNode extends AbstractRequestHandlerNode<OnlineRequest> {
    //用户已经在线
    private static final String IS_ALREADY_ONLINE = "isAlreadyOnline";
    //用户上线成功
    private static final String ONLINE_SUCCESS = "onlineSuccess";
    //上线失败
    private static final String ONLINE_FAIL = "onlineFail";

    private UserStateService userStateService;

    private UserService userService;

    private ServerNameBuilder serverNameBuilder;

    public String doNode(OnlineRequest onlineRequest, ValueStack valueStack) {
        String username = onlineRequest.getUsername();
        List<UserState> userStates = userStateService.getStates(username);
        List<UserState> newUserStates = new ArrayList<>();
        UserState userState = createUserState(onlineRequest);
        newUserStates.add(userState);
        if (userStates != null && userStates.size() > 0) {
            for (UserState us : userStates) {
                if (!StringUtils.equals(userState.getClientType(), us.getClientType())) {
                    newUserStates.add(us);
                }
            }
        }
        userStateService.online(username, newUserStates);
        return ONLINE_SUCCESS;
    }

    private UserState createUserState(OnlineRequest onlineRequest) {
        String nodeName = serverNameBuilder.getName();
        UserState userState = new UserState();
        userState.setUsername(onlineRequest.getUsername());
        userState.setConnectId(onlineRequest.getConnectId());
        userState.setClientType(onlineRequest.getClientType());
        userState.setServerName(nodeName);
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
