package org.roof.im.chain;

import com.roof.chain.api.ValueStack;
import org.apache.commons.lang3.StringUtils;
import org.roof.im.request.Request;
import org.roof.im.user.UserService;

/**
 * 根据token查询当前登录用户
 */
public class UserLoginNode {
    private static final String USER_LOGIN = "userLogin";
    private static final String USER_NOT_LOGIN = "userNotLogin";
    private UserService userService;

    public String doNode(Request request, ValueStack valueStack) {
        String token = request.getToken();
        String username = userService.getLoginUser(token);
        if (StringUtils.isBlank(username)) {
            return USER_NOT_LOGIN;
        } else {
            valueStack.set(ImConstant.CURRENT_USER, username);
            request.setUsername(username);
            return USER_LOGIN;
        }
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
