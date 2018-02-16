package org.roof.im.transport;

import org.roof.im.request.Request;

import java.util.concurrent.TimeUnit;

/**
 * 消息生产者
 */
public interface RequestPublisher<E extends Request> {

    boolean publish(E e, long timeout, TimeUnit unit)
            throws InterruptedException;

}
