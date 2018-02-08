package org.roof.im.connect;

/**
 * 连接服务器客户存储
 *
 * @param <T> 客户端对象
 * @author liuxin
 */
public interface ConnectStore<T> {
    /**
     * 根据连接id获取已经打开的连接
     *
     * @param connectId 连接id
     * @return 获取到的连接
     */
    T get(String connectId);

    /**
     * 保存已经打开的连接
     *
     * @param connectId 连接id
     * @param connect   连接对象
     */
    void put(String connectId, T connect);

    /**
     * 移除已经打开的连接
     * 移除并不会关闭连接
     *
     * @param connectId 连接id
     * @return 是否移除成功
     */
    boolean remove(String connectId);
}
