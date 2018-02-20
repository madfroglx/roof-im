package org.roof.im.user.impl;

import org.apache.commons.lang3.StringUtils;
import org.roof.im.user.TokenUsernameStore;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.util.Assert;

public class CacheTokenUsernameStore implements TokenUsernameStore, InitializingBean {
    public static final String TOKEN_USERNAME_STORE = "TokenUsernameStore";
    private Cache cache;
    private CacheManager cacheManager;

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(cacheManager, "cacheManager cannot be null");
        cache = cacheManager.getCache(TOKEN_USERNAME_STORE);
    }

    @Override
    public String get(String token) {
        return cache.get(token, String.class);
    }

    @Override
    public String put(String token, String username) {
        cache.put(token, username);
        return username;
    }

    @Override
    public String remove(String token) {
        String username = cache.get(token, String.class);
        if (StringUtils.isBlank(username)) {
            return null;
        }
        cache.evict(token);
        return username;
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }
}
