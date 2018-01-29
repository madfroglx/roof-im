package org.roof.im.session;

public interface SessionStore<T> {

    T get(String sessionID);

    void set(T webSocketSession);

    boolean remove(String sessionID);
}
