package org.roof.im.user.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.commons.lang3.StringUtils;
import org.roof.im.user.TokenUsernameStore;

public class CacheTokenUsernameStore implements TokenUsernameStore {
    private Cache<String, String> tokenUsernameToken;

    public CacheTokenUsernameStore() {
        tokenUsernameToken = CacheBuilder.newBuilder().build();
    }

    @Override
    public String get(String token) {
        return tokenUsernameToken.getIfPresent(token);
    }

    @Override
    public String put(String token, String username) {
        tokenUsernameToken.put(token, username);
        return username;
    }

    @Override
    public String remove(String token) {
        String username = tokenUsernameToken.getIfPresent(token);
        if (StringUtils.isBlank(username)) {
            return null;
        }
        tokenUsernameToken.invalidate(token);
        return username;
    }
}
