package org.roof.im.session;

public interface SessionManager {

    String open(String from, String to);

    boolean close(String sessionID);
}
