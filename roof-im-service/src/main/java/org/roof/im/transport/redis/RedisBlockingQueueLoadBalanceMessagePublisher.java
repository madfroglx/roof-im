package org.roof.im.transport.redis;

import org.roof.im.request.Request;
import org.roof.im.transport.queue.AbstractBlockingQueueLoadBalanceMessagePublisher;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.collections.DefaultRedisList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class RedisBlockingQueueLoadBalanceMessagePublisher<E extends Request> extends AbstractBlockingQueueLoadBalanceMessagePublisher {
    protected List<String> serverNames;
    protected List<RedisTemplate> redisTemplates;

    @Override
    protected void onInit() {
        Map<String, List<BlockingQueue<E>>> map = new HashMap<>();
        for (String serverName : serverNames) {
            List<BlockingQueue<E>> list = new ArrayList<>();
            for (RedisTemplate redisTemplate : redisTemplates) {
                BoundListOperations boundListOperations = redisTemplate.boundListOps(serverName);
                BlockingQueue blockingQueue = new DefaultRedisList(boundListOperations);
                list.add(blockingQueue);
            }
            queuesMap.put(serverName, list);
        }
        this.queuesMap = map;
    }

    public void setRedisTemplates(List<RedisTemplate> redisTemplates) {
        this.redisTemplates = redisTemplates;
    }

    public void setServerNames(List<String> serverNames) {
        this.serverNames = serverNames;
    }

}
