package org.roof.im.gateway;

import org.roof.im.response.Response;

public interface ResponseEndpoint {
    String send(Response response);
}
