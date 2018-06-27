package org.roof.im.chain;

import com.roof.chain.api.ValueStack;
import org.roof.im.request.Request;

/**
 * @author liuxin
 * @since 2018/6/27
 */
public class ServerAuthNode {
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

    public String doNode(Request request, ValueStack valueStack) {
        String token = request.getToken();
        String username = token;
        request.setUsername(username);
        valueStack.set(ImConstant.CURRENT_USER, username);
        return AUTH_SUCCESS;
    }
}
