package org.roof.im.transport.impl;

import org.roof.im.request.Request;
import org.roof.im.transport.LoadBalance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.RetryContext;

/**
 * org.roof.im.transport.redis
 * hashCode + 重试次数 计算出放队列的index
 * size为队列数量
 *
 * @author liht
 * @date 18/2/6
 */
public class HashCodeLoadBalance implements LoadBalance {

    private static final Logger LOGGER = LoggerFactory.getLogger(HashCodeLoadBalance.class);

    @Override
    public int select(Request request, RetryContext retryContext, int size) {
        if (request == null) {
            return -1;
        }
        if (request.getId() == null) {
            return -1;
        }
        int hashCode = Math.abs(request.getId().hashCode());
        int index = (hashCode + retryContext.getRetryCount()) % size;
        LOGGER.debug("produce to queue [" + index + "]");
        return index;
    }
}
