package org.roof.im.handler;

import org.roof.im.request.Request;
import org.roof.im.user.UserService;
import org.roof.im.user.UserStatusService;

public abstract class AbstractRequestHandler<T extends Request> implements RequestHandler<T> {
    //用户不存在
    protected String USER_NOT_EXISTS = "userNotExists";
    protected UserService userService;
    protected UserStatusService userStatusService;


    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public UserStatusService getUserStatusService() {
        return userStatusService;
    }

    public void setUserStatusService(UserStatusService userStatusService) {
        this.userStatusService = userStatusService;
    }
}
