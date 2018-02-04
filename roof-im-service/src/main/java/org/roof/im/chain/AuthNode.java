package org.roof.im.chain;

import com.roof.chain.api.ValueStack;
import org.apache.commons.lang3.StringUtils;
import org.roof.im.request.Request;
import org.roof.im.user.TokenUsernameStore;
import org.roof.im.user.UserAuthException;
import org.roof.im.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthNode {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthNode.class);
    /**
     * 用户认证成功
     */
    private static final String AUTH_SUCCESS = "authSuccess";
    /**
     * 用户认证异常
     */
    private static final String AUTH_ERROR = "authError";
    /**
     * 用户认证失败
     */
    private static final String AUTH_FAIL = "authFail";

    private UserService userService;
    private TokenUsernameStore tokenUsernameStore;

    public String doNode(Request request, ValueStack valueStack) {
        String username;
        String token = request.getToken();
        if (StringUtils.isEmpty(token)) {
            return AUTH_FAIL;
        }
        try {
            username = userService.auth(token);
        } catch (UserAuthException e) {
            LOGGER.error(e.getMessage(), e);
            return AUTH_ERROR;
        }
        if (StringUtils.isEmpty(username)) {
            return AUTH_FAIL;
        }
        tokenUsernameStore.put(token, username);
        request.setUsername(username);
        valueStack.set(ImConstant.CURRENT_USER, username);
        return AUTH_SUCCESS;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setTokenUsernameStore(TokenUsernameStore tokenUsernameStore) {
        this.tokenUsernameStore = tokenUsernameStore;
    }
}
