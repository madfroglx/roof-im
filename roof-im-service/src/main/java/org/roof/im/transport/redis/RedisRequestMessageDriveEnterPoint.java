package org.roof.im.transport.redis;

import org.roof.im.request.Request;
import org.roof.im.transport.ServerNameBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.collections.DefaultRedisList;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * org.roof.im.transport.redis
 * <p>
 * 使用Hystrix异步读取redis队列中的数据，执行对应逻辑操作
 * <p>
 * 本方法根据RedisList的数量进行
 *
 * @author liht
 * @date 18/2/7
 */
public class RedisRequestMessageDriveEnterPoint<E extends Request> implements InitializingBean, org.roof.im.transport.RequestSubscriber {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisRequestMessageDriveEnterPoint.class);
    private ServerNameBuilder serverNameBuilder;

    private static final int DEFAULT_POLL_THREAD_SIZE = 2;
    private List<BlockingQueue<E>> queues;
    private static final long DEFAULT_TIMEOUT = 1000;
    private long timeOut = DEFAULT_TIMEOUT;
    private long pollThreadSize = DEFAULT_POLL_THREAD_SIZE;
    private ThreadPoolExecutor executor;
    private List<RedisTemplate> redisTemplates;

    @Override
    public void afterPropertiesSet() throws Exception {
        queues = new ArrayList<>();
        String severName = serverNameBuilder.getName();
        for (RedisTemplate redisTemplate : redisTemplates) {
            BoundListOperations boundListOperations = redisTemplate.boundListOps(severName);
            DefaultRedisList redisList = new DefaultRedisList(boundListOperations);
            queues.add(redisList);
        }
        for (BlockingQueue<E> queue : queues) {
            for (int i = 0; i < pollThreadSize; i++) {
                executor.execute(new PollTask(queue));
            }
        }
    }

    @Override
    public void subscribe(Request request) {

    }

    class PollTask implements Runnable {
        private BlockingQueue<E> queue;

        public PollTask(BlockingQueue<E> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                E request = queue.poll(timeOut, TimeUnit.MILLISECONDS);
                subscribe(request);
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    public void setServerNameBuilder(ServerNameBuilder serverNameBuilder) {
        this.serverNameBuilder = serverNameBuilder;
    }

    public void setTimeOut(long timeOut) {
        this.timeOut = timeOut;
    }

    public void setPollThreadSize(long pollThreadSize) {
        this.pollThreadSize = pollThreadSize;
    }

    public void setExecutor(ThreadPoolExecutor executor) {
        this.executor = executor;
    }

    public void setRedisTemplates(List<RedisTemplate> redisTemplates) {
        this.redisTemplates = redisTemplates;
    }
}
