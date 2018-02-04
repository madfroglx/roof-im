package org.roof.im.user;

import java.util.List;

public interface UserStateService {

    List<UserState> getStatus(String username);

    boolean isOnline(String username);

    boolean online(String username, List<UserState> userState);

    boolean offline(String username);
}
