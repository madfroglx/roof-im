package org.roof.im.transport;

import org.springframework.retry.RetryContext;

/**
 * 负载均衡算法
 */
public interface LoadBalance {

    int select(Object value, RetryContext retryContext, int size);
}
