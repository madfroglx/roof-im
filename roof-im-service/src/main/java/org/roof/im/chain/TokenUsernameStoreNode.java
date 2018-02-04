package org.roof.im.chain;

import com.roof.chain.api.ValueStack;
import org.apache.commons.lang3.StringUtils;
import org.roof.im.request.Request;
import org.roof.im.user.TokenUsernameStore;

/**
 * 根据token查询当前登录用户
 */
public class TokenUsernameStoreNode {
    private static final String IN_STORE = "inStore";
    private static final String NOT_IN_STORE = "notInStore";
    private TokenUsernameStore tokenUsernameStore;

    public String doNode(Request request, ValueStack valueStack) {
        String token = request.getToken();
        String username = tokenUsernameStore.get(token);
        if (StringUtils.isBlank(username)) {
            return NOT_IN_STORE;
        } else {
            valueStack.set(ImConstant.CURRENT_USER, username);
            request.setUsername(username);
            return IN_STORE;
        }
    }

    public void setTokenUsernameStore(TokenUsernameStore tokenUsernameStore) {
        this.tokenUsernameStore = tokenUsernameStore;
    }
}
