package org.roof.im.transport.queue;

import org.roof.im.request.Request;
import org.roof.im.transport.LoadBalance;
import org.roof.im.transport.MessagePublisher;
import org.roof.im.transport.impl.HashCodeLoadBalance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 负载均衡消息队列
 *
 * @param <E>
 */
public abstract class AbstractBlockingQueueLoadBalanceMessagePublisher<E> implements MessagePublisher<E>, InitializingBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractBlockingQueueLoadBalanceMessagePublisher.class);
    /**
     * key : 机器名
     * value : 对应队列列表
     */
    protected Map<String, List<BlockingQueue<E>>> queuesMap;
    protected LoadBalance loadBalance = new HashCodeLoadBalance();
    protected static final long DEFAULT_TIMEOUT = 1000;
    protected long timeOut = DEFAULT_TIMEOUT;

    /**
     * retryTemplate
     */
    private RetryTemplate retryTemplate = new RetryTemplate();

    @Override
    public void afterPropertiesSet() throws Exception {
        onInit();
    }

    protected abstract void onInit();

    @Override
    public boolean publish(E e, String serverName, long timeout, TimeUnit unit) throws InterruptedException {
        return retryTemplate.execute(retryContext -> {
            List<BlockingQueue<E>> queues = queuesMap.get(serverName);
            Assert.notNull(queues, "server name: " + serverName + " queue cannot null");
            int index = loadBalance.select(e, retryContext, queues.size());
            return queues.get(index).offer(e, timeOut, TimeUnit.MILLISECONDS);
        });
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
