package org.roof.im.session;

import java.util.List;

/**
 * 会话管理
 * <p>
 * 会话指双方的一次聊天
 * </p>
 *
 * @author liuxin
 */
public interface SessionManager {
    /**
     * 开始一个会话
     *
     * @param sender    发起人
     * @param receiver  接收人
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 会话id
     */
    Session open(String sender, String receiver, long startTime, long endTime);

    /**
     * 结束一个会话
     *
     * @param id 会话id
     * @return 是否结束成功
     */
    boolean close(long id);

    /**
     * 查询用户间有效的会话
     *
     * @param sender   会话发起者
     * @param receiver 会话接受者
     * @return 会话
     */
    Session queryEffective(String sender, String receiver);

    /**
     * 查询用户未完成会话
     *
     * @param username 用户名
     * @return 有效会话列表
     */
    List<Session> queryIncomplete(String username);

}
