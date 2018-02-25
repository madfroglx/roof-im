package org.roof.im.transport;

import java.util.concurrent.TimeUnit;

/**
 * 消息生产者
 */
public interface MessagePublisher<E> {

    boolean publish(E e, String serverName, long timeout, TimeUnit unit)
            throws InterruptedException;

}
