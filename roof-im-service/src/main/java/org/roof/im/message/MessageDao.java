package org.roof.im.message;

import java.util.Date;
import java.util.List;

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
    int updateState(Long messageId, int state);

    /**
     * 查询最近的记录
     *
     * @param limit 查询数量
     * @return 记录列表
     */
    List<Message> queryLatest(String receiver, int limit);

    /**
     * 查询消息
     *
     * @param receiver 用户名
     * @param start    消息发送开始时间
     * @param end      消息发送结束时间
     * @param offset   记录偏移
     * @param limit    查询数量
     * @return 记录列表
     */
    List<Message> query(String receiver, Date start, Date end, int offset, int limit);

}
