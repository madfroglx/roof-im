package org.roof.im.session.map;

import org.apache.commons.lang3.StringUtils;
import org.roof.im.session.Session;
import org.roof.im.session.SessionManager;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MapSessionManager implements SessionManager {
    private static final Map<String, Session> SESSION_STORE = new HashMap<>();

    @Override
    public Session open(String sender, String receiver) {
        Session session = new Session();
        session.setId(UUID.randomUUID().toString());
        session.setSender(sender);
        session.setReceiver(receiver);
        session.setStart(System.currentTimeMillis());
        SESSION_STORE.put(session.getId(), session);
        return session;
    }

    @Override
    public boolean close(String id) {
        Session session = SESSION_STORE.get(id);
        if (session == null) {
            return false;
        }
        session.setEnd(System.currentTimeMillis());
        return true;
    }

    @Override
    public Session queryById(String id, Boolean effective) {
        Session session = SESSION_STORE.get(id);
        if (session == null) {
            return null;
        }
        if (effective) {
            if (session.getEnd() == 0) {
                return session;
            } else {
                return null;
            }
        } else {
            return session;
        }
    }

    @Override
    public Session effective(String sender, String receiver) {
        for (Session session : SESSION_STORE.values()) {
            if (StringUtils.equals(sender, session.getSender())
                    && StringUtils.equals(receiver, session.getReceiver())
                    && session.getEnd() == 0) {
                return session;
            }
        }
        return null;
    }
}
