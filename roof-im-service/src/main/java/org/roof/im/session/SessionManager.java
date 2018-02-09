package org.roof.im.session;

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
     * @param sender   发起人
     * @param receiver 接收人
     * @return 会话id
     */
    Session open(String sender, String receiver);

    /**
     * 结束一个会话
     *
     * @param id 会话id
     * @return 是否结束成功
     */
    boolean close(String id);

    /**
     * 通过会话ID查询会话
     *
     * @param id 会话id
     * @param effective session 是否有效, <code>null</code>都查
     * @return 会话
     */
    Session queryById(String id, Boolean effective);

    /**
     * 通过会话双方查询会话
     *
     * @param sender   会话发起者
     * @param receiver 会话接受者
     * @param effective session 是否有效 ， <code>null</code>都查
     * @return 会话
     */
    Session queryByUser(String sender, String receiver, Boolean effective);

}
