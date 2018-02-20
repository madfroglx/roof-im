package org.roof.im.user.impl;

import org.roof.im.user.UserAttributesStore;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

public class CacheUserAttributesStore implements UserAttributesStore, InitializingBean {

    private static final String USER_ATTRIBUTES_STORE = "userAttributesStore";
    private Cache cache;
    private CacheManager cacheManager;

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(cacheManager, "cacheManager cannot be null");
        cache = cacheManager.getCache(USER_ATTRIBUTES_STORE);
    }


    @Override
    public Map<String, Object> getAttributes(String username) {
        return cache.get(username, Map.class);
    }

    @Override
    public Object get(String username, String key) {
        Map<String, Object> attributes = getAttributes(username);
        if (attributes == null) {
            return null;
        }
        return attributes.get(key);
    }

    @Override
    public String getString(String username, String key) {
        Object val = get(username, key);
        if (val == null) {
            return null;
        }
        return val.toString();
    }

    @Override
    public <T> T get(String username, String key, Class<T> cls) {
        Object val = get(username, key);
        if (val == null) {
            return null;
        }
        return (T) val;
    }

    @Override
    public Map<String, Object> removeUser(String username) {
        Map<String, Object> attributes = getAttributes(username);
        if (attributes == null) {
            return null;
        } else {
            cache.evict(username);
            return attributes;
        }
    }

    @Override
    public Object remove(String username, String key) {
        Map<String, Object> attributes = getAttributes(username);
        if (attributes == null) {
            return null;
        }
        Object result = attributes.remove(key);
        cache.put(username, attributes);
        return result;
    }

    @Override
    public void put(String username, String key, Object value) {
        Map<String, Object> attributes = getAttributes(username);
        if (attributes == null) {
            attributes = new HashMap<>();
        }
        attributes.put(key, value);
        cache.put(username, attributes);
    }

    @Override
    public void putAttributes(String username, Map<String, Object> attributes) {
        cache.put(username, attributes);
    }


    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }
}
