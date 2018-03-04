package org.roof.im.session.jdbc;

import org.roof.im.session.Session;
import org.roof.im.session.SessionManager;

import java.util.List;

public class JdbcSessionManager implements SessionManager {

    private SessionDao sessionDao;

    @Override
    public Session open(String sender, String receiver, long startTime, long endTime) {
        Session session = new Session();
        session.setSender(sender);
        session.setReceiver(receiver);
        session.setStartTime(startTime == 0 ? System.currentTimeMillis() : startTime);
        session.setEndTime(endTime);
        session.setState(0);
        sessionDao.save(session);
        return session;
    }

    @Override
    public boolean close(long id) {
        try {
            sessionDao.updateState(id, 2);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    @Override
    public Session queryEffective(String sender, String receiver) {
        return sessionDao.queryEffective(sender, receiver);
    }

    @Override
    public List<Session> queryAllEffective(String username) {
        return null;
    }

    public void setSessionDao(SessionDao sessionDao) {
        this.sessionDao = sessionDao;
    }
}
