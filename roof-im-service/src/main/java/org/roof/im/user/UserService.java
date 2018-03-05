package org.roof.im.user;

import org.apache.commons.lang3.StringUtils;

public interface UserService {

    String USERNAME_SPLIT = "##";

    /**
     * 认证
     * 使用token认证用户,并返回用户名. 将认证结果保存本地缓存
     *
     * @param token 用户凭证
     * @return 用户名
     */
    String auth(String token) throws UserAuthException;

    /**
     * 获得已登录用户名
     *
     * @param token 用户凭证
     * @return 已登录用户名
     */
    String getLoginUser(String token);

    /**
     * 用户是否存在
     *
     * @param username 用户名
     * @return 是否存在
     */
    boolean exist(String username);

    static String joinUsername(String u1, String u2) {
        if (StringUtils.compare(u1, u2) < 0) {
            return u1 + USERNAME_SPLIT + u2;
        } else {
            return u2 + USERNAME_SPLIT + u1;
        }
    }

}
