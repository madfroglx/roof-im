package org.roof.im.transport.redis;

import org.roof.im.request.Request;
import org.roof.im.transport.LoadBalance;
import org.roof.im.transport.RequestPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.collections.DefaultRedisList;
import org.springframework.retry.support.RetryTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 负载均衡消息队列
 *
 * @param <E>
 */
public class RedisLoadBalanceRequestPublisher<E extends Request> implements RequestPublisher<E>, InitializingBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisLoadBalanceRequestPublisher.class);
    /**
     * key : 机器名
     * value : 对应队列列表
     */
    private Map<String, List<BlockingQueue<E>>> queuesMap;
    private LoadBalance loadBalance;
    private static final long DEFAULT_TIMEOUT = 1000;
    private long timeOut = DEFAULT_TIMEOUT;
    private List<RedisTemplate> redisTemplates;
    private List<String> serverNames;

    /**
     * retryTemplate
     */
    private RetryTemplate retryTemplate = new RetryTemplate();

    @Override
    public void afterPropertiesSet() throws Exception {
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

    @Override
    public boolean produce(E e, long timeout, TimeUnit unit) throws InterruptedException {
        retryTemplate.execute(retryContext -> {
            List<BlockingQueue<E>> queues = queuesMap.get(e.getServerName());
            int index = loadBalance.select(e, retryContext, queues.size());
            return queues.get(index).offer(e, timeOut, TimeUnit.MILLISECONDS);
        });
        return false;
    }

    public void setLoadBalance(LoadBalance loadBalance) {
        this.loadBalance = loadBalance;
    }

    public void setTimeOut(long timeOut) {
        this.timeOut = timeOut;
    }

    public void setRetryTemplate(RetryTemplate retryTemplate) {
        this.retryTemplate = retryTemplate;
    }


}
