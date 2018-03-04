package org.roof.im.session.jdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.roof.im.session.Session;

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
    int updateState(long id, int state);


}
