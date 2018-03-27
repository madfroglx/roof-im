package org.roof.im.user;

/**
 * token用户名缓存
 * <p>
 * 集群缓存<br />
 * 一段时间不不访问该缓存自动失效, 失效后需要用户重新通过认证服务器认证
 * </p>
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
    String remove(String token);
}
