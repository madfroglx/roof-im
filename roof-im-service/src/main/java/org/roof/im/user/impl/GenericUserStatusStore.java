package org.roof.im.user.impl;

import org.roof.im.chain.ImConstant;
import org.roof.im.user.UserAttributesStore;
import org.roof.im.user.UserState;
import org.roof.im.user.UserStatusStore;

import java.util.List;

public class GenericUserStatusStore implements UserStatusStore {

    private UserAttributesStore userAttributesStore;

    @Override
    public void set(String username, List<UserState> userStates) throws Exception {
        userAttributesStore.put(username, ImConstant.USER_STATES, userStates);
    }

    @Override
    public List<UserState> get(String username) throws Exception {
        return userAttributesStore.get(username, ImConstant.USER_STATES, List.class);
    }

    @Override
    public boolean remove(String username) throws Exception {
        return !(userAttributesStore.remove(username, ImConstant.USER_STATES) == null);
    }

    public void setUserAttributesStore(UserAttributesStore userAttributesStore) {
        this.userAttributesStore = userAttributesStore;
    }
}
