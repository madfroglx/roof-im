package org.roof.im.handler;

import org.roof.im.request.Request;

public interface RequestHandler<T extends Request> {

    String receive(T request);
}
