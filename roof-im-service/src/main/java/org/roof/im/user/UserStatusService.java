package org.roof.im.user;

public interface UserStatusService {

    UserStatus getStatus(String username);

    boolean isOnline(String username);

    boolean online(String username, UserStatus userStatus);

    boolean offline(String username);
}
