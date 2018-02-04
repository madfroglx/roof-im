package org.roof.im.user.impl;

import org.roof.im.user.UserState;
import org.roof.im.user.UserStateService;
import org.roof.im.user.UserStatusStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SimpleUserStateService implements UserStateService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleUserStateService.class);
    private UserStatusStore userStatusStore;

    @Override
    public List<UserState> getStatus(String username) {
        try {
            return userStatusStore.get(username);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public boolean isOnline(String username) {
        return !(getStatus(username) == null);
    }

    @Override
    public boolean online(String username, List<UserState> userStates) {
        try {
            userStatusStore.set(username, userStates);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    @Override
    public boolean offline(String username) {
        try {
            userStatusStore.remove(username);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    public UserStatusStore getUserStatusStore() {
        return userStatusStore;
    }

    public void setUserStatusStore(UserStatusStore userStatusStore) {
        this.userStatusStore = userStatusStore;
    }
}
