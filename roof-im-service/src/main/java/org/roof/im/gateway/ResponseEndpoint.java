package org.roof.im.gateway;

import org.roof.im.response.Response;

import java.io.IOException;

/**
 * 向客户端发送消息
 *
 * @author liuxin
 */
public interface ResponseEndpoint {
    void send(String connectID, Response response) throws IOException;
}
