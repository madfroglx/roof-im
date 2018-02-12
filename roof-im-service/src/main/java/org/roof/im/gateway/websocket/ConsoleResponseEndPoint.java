package org.roof.im.gateway.websocket;

import com.alibaba.fastjson.JSON;
import org.roof.im.gateway.ResponseEndpoint;
import org.roof.im.response.Response;

import java.io.IOException;

public class ConsoleResponseEndPoint implements ResponseEndpoint {

    @Override
    public void send(String connectId, Response response) throws IOException {
        System.out.println("connectId : " + connectId + ", response : " + JSON.toJSONString(response));
    }
}
