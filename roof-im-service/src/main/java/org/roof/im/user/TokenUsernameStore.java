package org.roof.im.user;

/**
 * 用户
 */
public interface TokenUsernameStore {
    /**
     * 根据token获取本地缓存的username
     *
     * @param token
     * @return 获取到的用户名
     */
    String get(String token);

    /**
     * 将token-username 加入缓存
     */
    String put(String token, String username);

    /**
     * 删除
     */
    String remove(String username);
}
