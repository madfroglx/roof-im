package org.roof.im.connect;

import java.io.IOException;

/**
 * 连接管理
 *
 * @param <T> 链接对象
 */
public interface ConnectManager<T> extends ConnectStore<T> {
    /**
     * 关闭连接
     *
     * @param connectId 连接ID
     * @return 关闭成功返回连接对象，返回<code>null</code>表示连接不存在
     * @throws IOException 连接关闭时抛出异常
     */
    T close(String connectId) throws IOException;
}