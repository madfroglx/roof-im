package org.roof.im.user;

import java.util.Map;

/**
 * 用户属性存储
 * <p>
 * 集群公共存储<br />
 * 有一定生效时间默认10分钟, 根据用户心跳或者存储维持缓存
 * </p>
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
