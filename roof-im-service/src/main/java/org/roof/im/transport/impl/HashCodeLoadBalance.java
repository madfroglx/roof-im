package org.roof.im.transport.impl;

import org.roof.im.transport.LoadBalance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.RetryContext;

/**
 * org.roof.im.transport.queue
 * hashCode + 重试次数 计算出放队列的index
 * size为队列数量
 *
 * @author liht
 * @date 18/2/6
 */
public class HashCodeLoadBalance implements LoadBalance {

    private static final Logger LOGGER = LoggerFactory.getLogger(HashCodeLoadBalance.class);

    @Override
    public int select(Object value, RetryContext retryContext, int size) {
        if (value == null) {
            return -1;
        }
        int hashCode = Math.abs(value.hashCode());
        int index = (hashCode + retryContext.getRetryCount()) % size;
        LOGGER.debug("publish to queue [" + index + "]");
        return index;
    }
}
