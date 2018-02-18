package org.roof.im.user.impl;

import org.roof.im.user.UserAttributesStore;
import org.roof.im.user.UserState;
import org.roof.im.user.UserStatusStore;

import java.util.List;

public class GenericUserStatusStore implements UserStatusStore {

    private static final String USER_STATES = "userStates";
    private UserAttributesStore userAttributesStore;

    @Override
    public void set(String username, List<UserState> userStates) throws Exception {
        userAttributesStore.put(username, USER_STATES, userStates);
    }

    @Override
    public List<UserState> get(String username) throws Exception {
        return userAttributesStore.get(username, USER_STATES, List.class);
    }

    @Override
    public boolean remove(String username) throws Exception {
        return !(userAttributesStore.remove(username, USER_STATES) == null);
    }

    public void setUserAttributesStore(UserAttributesStore userAttributesStore) {
        this.userAttributesStore = userAttributesStore;
    }
}
