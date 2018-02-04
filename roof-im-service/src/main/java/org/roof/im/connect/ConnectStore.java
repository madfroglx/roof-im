package org.roof.im.connect;

/**
 * 连接服务器客户存储
 *
 * @param <T> 客户端对象
 */
public interface ConnectStore<T> {

    T get(String connectID);

    void set(T connect);

    boolean remove(String connectID);
}
