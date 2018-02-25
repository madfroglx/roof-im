package org.roof.im.transport;

/**
 * 请求订阅
 *
 * @param <E>
 */
public interface RequestSubscriber<E> {
    void subscribe(E message);
}
