package org.roof.im.user.impl;

import org.roof.im.user.UserStatus;
import org.roof.im.user.UserStatusStore;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapUserStatusStore implements UserStatusStore {

    private Map<String, UserStatus> userStatusMap = new ConcurrentHashMap<>();

    @Override
    public void set(UserStatus userStatus) throws Exception {
        userStatusMap.put(userStatus.getUsername(), userStatus);
    }

    @Override
    public UserStatus get(String username) throws Exception {
        return userStatusMap.get(username);
    }

    @Override
    public boolean remove(String username) throws Exception {
        return !(userStatusMap.remove(username) == null);
    }
}
