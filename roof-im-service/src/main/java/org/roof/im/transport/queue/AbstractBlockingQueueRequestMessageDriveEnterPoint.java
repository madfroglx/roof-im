package org.roof.im.transport.queue;

import com.roof.chain.api.Chain;
import com.roof.chain.api.ValueStack;
import com.roof.chain.support.GenericValueStack;
import org.roof.im.chain.ImConstant;
import org.roof.im.request.Request;
import org.roof.im.transport.ServerNameBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/**
 * org.roof.im.transport.queue
 * <p>
 * 使用Hystrix异步读取redis队列中的数据，执行对应逻辑操作
 * <p>
 * 本方法根据RedisList的数量进行
 *
 * @author liht
 * @date 18/2/7
 */
public abstract class AbstractBlockingQueueRequestMessageDriveEnterPoint<E extends Request> implements InitializingBean, org.roof.im.transport.RequestSubscriber {
    protected static final int DEFAULT_POLL_THREAD_SIZE = 2;
    protected static final long DEFAULT_TIMEOUT = 1000;
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractBlockingQueueRequestMessageDriveEnterPoint.class);
    protected ServerNameBuilder serverNameBuilder;
    protected List<BlockingQueue<E>> queues;
    protected long timeOut = DEFAULT_TIMEOUT;
    protected long pollThreadSize = DEFAULT_POLL_THREAD_SIZE;
    protected Executor executor;

    protected Chain chain;

    @Override
    public void afterPropertiesSet() throws Exception {
        onInit();
        for (BlockingQueue<E> queue : queues) {
            for (int i = 0; i < pollThreadSize; i++) {
                executor.execute(new PollTask(queue));
            }
        }
    }

    protected abstract void onInit();

    @Override
    public void subscribe(Request request) {
        ValueStack valueStack = new GenericValueStack();
        valueStack.set(ImConstant.REQUEST, request);
        try {
            chain.doChain(valueStack);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    class PollTask implements Runnable {
        private BlockingQueue<E> queue;

        public PollTask(BlockingQueue<E> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    E request = queue.poll(timeOut, TimeUnit.MILLISECONDS);
                    if (request == null) {
                        continue;
                    }
                    subscribe(request);
                } catch (InterruptedException e) {
                    LOGGER.error(e.getMessage(), e);
                }
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

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    public void setChain(Chain chain) {
        this.chain = chain;
    }

    public void setQueues(List<BlockingQueue<E>> queues) {
        this.queues = queues;
    }
}
