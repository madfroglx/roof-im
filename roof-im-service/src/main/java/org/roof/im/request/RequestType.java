package org.roof.im.request;

/**
 * 消息类型
 *
 * @author liuxin
 */
public enum RequestType {
    /**
     * 上线
     */
    online,
    /**
     * 下线
     */
    offline,
    /**
     * 打开session
     */
    openSession,
    /**
     * 关闭session
     */
    closeSession,
    /**
     * 发送消息
     */
    message
}
