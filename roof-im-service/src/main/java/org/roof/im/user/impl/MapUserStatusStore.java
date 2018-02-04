package org.roof.im.user.impl;

import org.roof.im.user.UserState;
import org.roof.im.user.UserStatusStore;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapUserStatusStore implements UserStatusStore {

    private Map<String, List<UserState>> userStatusMap = new ConcurrentHashMap<>();

    @Override
    public void set(String username, List<UserState> userStates) throws Exception {
        userStatusMap.put(username, userStates);
    }

    @Override
    public List<UserState> get(String username) throws Exception {
        return userStatusMap.get(username);
    }

    @Override
    public boolean remove(String username) throws Exception {
        return !(userStatusMap.remove(username) == null);
    }
}
