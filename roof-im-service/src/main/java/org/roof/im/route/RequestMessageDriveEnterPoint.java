package org.roof.im.route;

/**
 * 获取到本服务节点处理处理得消息，并交给处理逻辑处理
 */
public interface RequestMessageDriveEnterPoint {
    void receive();
}
