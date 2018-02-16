package org.roof.im.converter;

import org.roof.im.request.Request;

import java.util.UUID;

public class UUIDRequestIdGenerator implements RequestIdGenerator {
    @Override
    public String generateId(Request request) {
        return UUID.randomUUID().toString();
    }
}
