package org.roof.im.chain.handler;

import org.roof.im.request.Request;
import org.roof.im.user.UserService;
import org.roof.im.user.UserStateService;

public abstract class AbstractRequestHandlerNode<T extends Request> implements RequestHandlerNode<T> {
    //用户不存在
    protected String USER_NOT_EXISTS = "userNotExists";
    protected UserService userService;
    protected UserStateService userStateService;


    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public UserStateService getUserStateService() {
        return userStateService;
    }

    public void setUserStateService(UserStateService userStateService) {
        this.userStateService = userStateService;
    }
}
