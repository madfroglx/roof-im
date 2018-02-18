package org.roof.im.user;

import java.util.Map;

/**
 * 用户属性存储
 */
public interface UserAttributesStore {
    Map<String, Object> getAttributes(String username);

    Object get(String username, String key);

    String getString(String username, String key);

    <T> T get(String username, String key, Class<T> cls);

    Map<String, Object> removeUser(String username);

    Object remove(String username, String key);

    void put(String username, String key, Object value);

    void putAttributes(String username, Map<String, Object> attributes);

}
