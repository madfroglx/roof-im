package org.roof.im.session.jdbc;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.roof.im.session.Session;

import java.util.List;

@MapperScan
public interface SessionDao {
    /**
     * 保存
     *
     * @param session session对象
     * @return 影响行数
     */
    int save(Session session);

    /**
     * 根据ID加载session
     *
     * @param id
     * @return session对象
     */
    Session load(long id);

    /**
     * 更新状态
     *
     * @param id
     * @param state 状态
     * @return 影响行数
     */
    Integer updateState(@Param("id") long id, @Param("state") int state);

    /**
     * 查询双方有效会话
     *
     * @param sender   发送方
     * @param receiver 接收方
     * @return session对象
     */
    Session queryEffective(@Param("sender") String sender, @Param("receiver") String receiver);

    /**
     * 查询一个用户的有效会话
     *
     * @param username
     * @return
     */
    List<Session> queryAllEffective(String username);


}
