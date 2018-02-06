package org.roof.im.route.redis;

import org.roof.im.request.Request;
import org.roof.im.route.OfferIndexPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.RetryContext;

/**
 * org.roof.im.route.redis
 * hashCode + 重试次数 计算出放队列的index
 * size为队列数量
 *
 * @author liht
 * @date 18/2/6
 */
public class HashCodeOfferIndexPolicy implements OfferIndexPolicy {

    private static final Logger LOGGER = LoggerFactory.getLogger(HashCodeOfferIndexPolicy.class);

    @Override
    public int offerIndex(Request request, RetryContext retryContext, int size) {
        if (request == null) {
            return 0;
        }
        if (request.getId() == null) {
            return 0;
        }
        int hashCode = Math.abs(request.getId().toString().hashCode());
        int index = (hashCode + retryContext.getRetryCount()) % size;
        LOGGER.debug("offer to queue [" + index + "]");
        return index;
    }
}
