package org.roof.im.route;

import org.roof.im.request.Request;
import org.springframework.retry.RetryContext;

/**
 *
 */
public interface OfferIndexPolicy {

    int offerIndex(Request request, RetryContext retryContext,int size);
}
