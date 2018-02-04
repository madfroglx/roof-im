package org.roof.im.route.redis;

import org.roof.im.request.Request;
import org.roof.im.route.ServerNameBuilder;
import org.roof.im.route.RequestRouter;
import org.springframework.data.redis.support.collections.RedisList;

import java.util.List;

public class RedisRequestRouter implements RequestRouter {
    private List<RedisList> lists;
    private ServerNameBuilder serverNameBuilder;

    @Override
    public boolean route(Request request) {
        String nodeName = serverNameBuilder.getName();
        return false;
    }
}
