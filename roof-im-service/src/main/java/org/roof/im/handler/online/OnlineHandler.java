package org.roof.im.handler.online;

import org.roof.im.handler.AbstractRequestHandler;
import org.roof.im.request.OnlineRequest;
import org.roof.im.response.Response;
import org.roof.im.route.NodeNameBuilder;
import org.roof.im.user.UserService;
import org.roof.im.user.UserStatus;
import org.roof.im.user.UserStatusService;

public class OnlineHandler extends AbstractRequestHandler<OnlineRequest> {
    //用户已经在线
    private static final String IS_ALREADY_ONLINE = "isAlreadyOnline";
    //用户上线成功
    private static final String ONLINE_SUCCESS = "onlineSuccess";
    //上线失败
    private static final String ONLINE_FAIL = "onlineFail";

    private UserStatusService userStatusService;

    private UserService userService;

    private NodeNameBuilder nodeNameBuilder;

    public String receive(OnlineRequest onlineRequest) {
        String username = onlineRequest.getUsername();
        if (!userService.exist(username)) {
            return USER_NOT_EXISTS;
        }
        String nodeName = nodeNameBuilder.getNodeName();
        UserStatus userStatus = userStatusService.getStatus(username);
        if (userStatus != null) {
            if (nodeName.equals(userStatus.getNodeName())) {
                return IS_ALREADY_ONLINE;
            }
            //TODO 在不同的服务器建立连接
        }
        userStatus = new UserStatus();
        userStatus.setUsername(onlineRequest.getUsername());
        userStatus.setSessionID(onlineRequest.getSessionID());
        if (userStatusService.online(onlineRequest.getUsername(), userStatus)) {
            return ONLINE_FAIL;
        }
        return ONLINE_SUCCESS;
    }

    public UserStatusService getUserStatusService() {
        return userStatusService;
    }

    public void setUserStatusService(UserStatusService userStatusService) {
        this.userStatusService = userStatusService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setNodeNameBuilder(NodeNameBuilder nodeNameBuilder) {
        this.nodeNameBuilder = nodeNameBuilder;
    }
}
