package org.roof.im.user;

/**
 * @author hzliuxin1
 * @since 2018/3/13 0013
 */
public interface OnlineUserStore {

    void add(String username);

    boolean remove(String username);

    String[] get();
}
