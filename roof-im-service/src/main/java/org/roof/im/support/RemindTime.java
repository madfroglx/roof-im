package org.roof.im.support;

import org.roof.im.session.Session;

/**
 * 结束时间提醒
 *
 * @author liuxin
 * @since 2018/4/13
 */
public interface RemindTime {
    /**
     * 获得结束提醒时间
     *
     * @param session 会话
     * @return
     */
    int getRemindTime(Session session);
}
