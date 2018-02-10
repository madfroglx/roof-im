package org.roof.im.route;

import org.roof.im.request.Request;
import org.roof.im.user.UserState;

import java.util.List;

/**
 * 将消息路由到服务器节点
 */
public interface RequestRouter {
    /**
     * 将用户消息, 根据用户在线状态路由到指定的服务器队列
     *
     * @param request    请求
     * @param userStates 用户状态
     * @return
     * @throws Throwable
     */
    boolean route(Request request, List<UserState> userStates) throws Throwable;
}
