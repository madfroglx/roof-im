package org.roof.im.chain.handler;

import com.roof.chain.api.ValueStack;
import org.roof.im.request.Request;

/**
 * 消息处理节点
 *
 * @param <T>
 */
public interface RequestHandlerNode<T extends Request> {

    Object doNode(T request, ValueStack valueStack);
}
