package org.roof.im.user;

public interface UserService {
    /**
     * 认证
     *
     * @param token 用户凭证
     * @return 用户名
     */
    String auth(String token) throws UserAuthException;

    /**
     * 用户是否存在
     *
     * @param username 用户名
     * @return 是否存在
     */
    boolean exist(String username);

}
