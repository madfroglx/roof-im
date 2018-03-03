package org.roof.im.message;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import java.util.Date;
import java.util.List;

@MapperScan
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
     * 查询未收取消息,并标记为已读
     *
     * @param limit 查询数量
     * @return 记录列表
     */
    List<Message> queryNotReceived(String receiver, int limit);

    /**
     * 查询消息
     *
     * @param receiver  用户名
     * @param sender    消息发送者
     * @param startTime 消息发送开始时间
     * @param endTime   消息发送结束时间
     * @param offset    记录偏移
     * @param limit     查询数量
     * @return 记录列表
     */
    List<Message> query(@Param("receiver") String receiver, @Param("sender") String sender,
                        @Param("startTime") long startTime, @Param("endTime") long endTime, @Param("offset") int offset,
                        @Param("limit") int limit);

}
