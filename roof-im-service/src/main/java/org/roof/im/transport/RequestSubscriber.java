package org.roof.im.transport;

import org.roof.im.request.Request;

/**
 * 请求订阅
 *
 * @param <E>
 */
public interface RequestSubscriber<E extends Request> {
    void subscribe(E request);
}
