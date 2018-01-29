package org.roof.im.route.redis;

import org.roof.im.request.Request;
import org.roof.im.route.NodeNameBuilder;
import org.roof.im.route.RequestList;
import org.springframework.data.redis.support.collections.RedisList;

import java.util.List;

public class RedisRequestList implements RequestList {
    private List<RedisList> lists;
    private NodeNameBuilder nodeNameBuilder;

    @Override
    public boolean add(Request request) {
        String nodeName = nodeNameBuilder.getNodeName();
        return false;
    }
}
