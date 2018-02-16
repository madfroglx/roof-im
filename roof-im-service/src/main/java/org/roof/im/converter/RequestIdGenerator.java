package org.roof.im.converter;

import org.roof.im.request.Request;

public interface RequestIdGenerator {
    String generateId(Request request);
}
