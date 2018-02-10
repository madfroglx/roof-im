package org.roof.im.transport;

import org.roof.im.request.Request;
import org.springframework.retry.RetryContext;

/**
 *负载均衡算法
 */
public interface LoadBalance {

    int select(Request request, RetryContext retryContext, int size);
}
