package org.roof.im.user.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.roof.im.user.UserAttributesStore;

import java.util.Map;

public class CacheUserStore implements UserAttributesStore {

    private Cache<String, Map<String, Object>> cache;

    public CacheUserStore() {
        cache = CacheBuilder.newBuilder().build();
    }

    @Override
    public Map<String, Object> getAttributes(String username) {
        return cache.getIfPresent(username);
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
            cache.invalidate(username);
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
        attributes.put(key, value);
        cache.put(username, attributes);
    }

    @Override
    public void putAttributes(String username, Map<String, Object> attributes) {
        cache.put(username, attributes);
    }
}
