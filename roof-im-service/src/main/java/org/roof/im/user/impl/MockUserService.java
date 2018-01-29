package org.roof.im.user.impl;


import org.roof.im.user.UserService;

public class MockUserService implements UserService {
    @Override
    public boolean exist(String username) {
        return true;
    }
}
