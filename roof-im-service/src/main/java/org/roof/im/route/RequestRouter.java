package org.roof.im.route;

import org.roof.im.request.Request;

/**
 * 将消息路由到服务器节点
 */
public interface RequestRouter {

    boolean route(Request request);
}
