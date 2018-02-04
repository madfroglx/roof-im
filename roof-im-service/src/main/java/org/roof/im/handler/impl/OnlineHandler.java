package org.roof.im.handler.impl;

import org.apache.commons.lang3.StringUtils;
import org.roof.im.handler.AbstractRequestHandler;
import org.roof.im.request.OnlineRequest;
import org.roof.im.route.ServiceNameBuilder;
import org.roof.im.user.UserService;
import org.roof.im.user.UserState;
import org.roof.im.user.UserStateService;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class OnlineHandler extends AbstractRequestHandler<OnlineRequest> {
    //用户已经在线
    private static final String IS_ALREADY_ONLINE = "isAlreadyOnline";
    //用户上线成功
    private static final String ONLINE_SUCCESS = "onlineSuccess";
    //上线失败
    private static final String ONLINE_FAIL = "onlineFail";

    private UserStateService userStateService;

    private UserService userService;

    private ServiceNameBuilder serviceNameBuilder;

    public String receive(OnlineRequest onlineRequest) {
        String username = onlineRequest.getUsername();
        List<UserState> userStates = userStateService.getStatus(username);
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
        String nodeName = serviceNameBuilder.getName();
        UserState userState = new UserState();
        userState.setUsername(onlineRequest.getUsername());
        userState.setConnectID(onlineRequest.getConnectID());
        userState.setClientType(onlineRequest.getClientType());
        userState.setNodeName(nodeName);
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

    public void setServiceNameBuilder(ServiceNameBuilder serviceNameBuilder) {
        this.serviceNameBuilder = serviceNameBuilder;
    }
}
