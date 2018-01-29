package org.roof.im.user;

public interface UserStatusStore {

    void set(UserStatus userStatus) throws Exception;

    UserStatus get(String username) throws Exception;

    boolean remove(String username) throws Exception;
}
