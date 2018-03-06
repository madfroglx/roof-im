package org.roof.im.transport.redis;

import org.roof.im.transport.ServerNameBuilder;
import org.roof.im.transport.queue.AbstractBlockingQueueRequestMessageDriveEnterPoint;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.collections.DefaultRedisList;
import org.springframework.data.redis.support.collections.RedisList;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hzliuxin1
 * @since 2018/3/6 0006
 */
public class RedisBlockingQueueRequestMessageDriveEnterPoint<E> extends AbstractBlockingQueueRequestMessageDriveEnterPoint {
    protected List<RedisTemplate> redisTemplates;

    @Override
    protected void onInit() {
        this.queues = new ArrayList();
        for (RedisTemplate redisTemplate : redisTemplates) {
            RedisList<E> redisList = new DefaultRedisList<E>(redisTemplate.boundListOps(serverNameBuilder.getName()));
            this.queues.add(redisList);
        }
    }

    @Override
    public void setServerNameBuilder(ServerNameBuilder serverNameBuilder) {
        this.serverNameBuilder = serverNameBuilder;
    }
}
