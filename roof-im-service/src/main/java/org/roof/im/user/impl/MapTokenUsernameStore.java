package org.roof.im.user.impl;

import org.roof.im.user.TokenUsernameStore;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapTokenUsernameStore implements TokenUsernameStore {
    private Map<String, String> tokenUsernameToken = new ConcurrentHashMap<>();

    @Override
    public String get(String token) {
        return tokenUsernameToken.get(token);
    }

    @Override
    public String put(String token, String username) {
        return tokenUsernameToken.put(token, username);
    }

    @Override
    public String remove(String username) {
        return tokenUsernameToken.remove(username);
    }
}
