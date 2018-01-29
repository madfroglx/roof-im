package org.roof.im.user.impl;

import org.roof.im.user.UserStatus;
import org.roof.im.user.UserStatusService;
import org.roof.im.user.UserStatusStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleUserStatusService implements UserStatusService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleUserStatusService.class);
    private UserStatusStore userStatusStore;

    @Override
    public UserStatus getStatus(String username) {
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
    public boolean online(String username, UserStatus userStatus) {
        try {
            userStatusStore.set(userStatus);
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
