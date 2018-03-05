package org.roof.im.user.impl;


import org.roof.im.user.TokenUsernameStore;
import org.roof.im.user.UserAuthException;
import org.roof.im.user.UserService;

public class MockUserService implements UserService {
    private TokenUsernameStore tokenUsernameStore;

    @Override
    public String auth(String token) throws UserAuthException {
        String username = token;
        username = username.toLowerCase();
        tokenUsernameStore.put(token, username);
        return username;
    }

    @Override
    public String getLoginUser(String token) {
        return tokenUsernameStore.get(token);
    }

    @Override
    public boolean exist(String username) {
        return true;
    }

    public void setTokenUsernameStore(TokenUsernameStore tokenUsernameStore) {
        this.tokenUsernameStore = tokenUsernameStore;
    }
}
