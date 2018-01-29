package org.roof.im.gateway;

public interface RequestEnterPoint {
    void receive(String sessionID, String message);
}
