package org.roof.im.user;

import java.util.List;

public interface UserStatusStore {

    void set(String username, List<UserState> userStates) throws Exception;

    List<UserState> get(String username) throws Exception;

    boolean remove(String username) throws Exception;
}
