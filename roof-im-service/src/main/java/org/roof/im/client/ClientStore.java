package org.roof.im.client;

/**
 * 连接服务器客户存储
 *
 * @param <T> 客户端对象
 */
public interface ClientStore<T> {

    T get(String sessionID);

    void set(T session);

    boolean remove(String sessionID);
}
