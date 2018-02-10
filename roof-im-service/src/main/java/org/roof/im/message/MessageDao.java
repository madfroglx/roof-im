package org.roof.im.message;

public interface MessageDao {
    /**
     * 保存消息
     *
     * @param message 消息
     */
    void save(Message message);

    /**
     * 更新消息状态
     *
     * @param messageId 消息id
     * @param state     当前状态
     */
    void updateState(Long messageId, int state);

}
