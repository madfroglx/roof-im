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
     * 隐身
     */
    hide,
    /**
     * 打开session
     */
    openSession,
    /**
     * 关闭session
     */
    closeSession,
    /**
     * 查询session
     */
    querySession,
    /**
     * 开始session
     */
    startSession,
    /**
     * 发送消息
     */
    message,
    /**
     * 拉取消息
     */
    pullMessage,
    /**
     * 未收取的消息
     */
    pullNotReceivedMessage,
    /**
     * 获取上传文件临时密钥
     */
    getTmpSecret,
    /**
     * 心跳
     */
    heart
}
