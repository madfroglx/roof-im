package org.roof.im.message;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import java.util.Date;
import java.util.List;

@MapperScan
//TODO 改为MessageManager 与数据库解耦
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
    int updateState(@Param("messageId") Long messageId, @Param("state") int state);

    /**
     * 批量更新消息状态
     *
     * @param messageIds 消息id数组
     * @param state      当前状态
     * @return 影响行数
     */
    int updateStateBatch(@Param("messageIds") long[] messageIds, @Param("state") int state);

    /**
     * 查询未收取消息,并标记为已读
     *
     * @param sender 消息发送者
     * @param limit  查询数量
     * @return 记录列表
     */
    List<Message> queryNotReceived(@Param("receiver") String receiver,
                                   @Param("sender") String sender, @Param("limit") int limit);

    /**
     * 查询消息
     *
     * @param receiver  用户名
     * @param sender    消息发送者
     * @param startTime 消息发送开始时间
     * @param endTime   消息发送结束时间
     * @param state     消息状态
     * @param offset    记录偏移
     * @param limit     查询数量
     * @return 记录列表
     */
    //TODO 解决sql OR问题
    List<Message> query(@Param("receiver") String receiver, @Param("sender") String sender,
                        @Param("startTime") long startTime, @Param("endTime") long endTime,
                        @Param("state") Integer state,
                        @Param("offset") int offset, @Param("limit") int limit);

}
