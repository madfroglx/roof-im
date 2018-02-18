package org.roof.im.connect;

import org.apache.commons.lang3.StringUtils;
import org.roof.im.user.UserService;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * 建立连接前验证token是否有效
 */
public class TokenAuthHandshakeInterceptor implements HandshakeInterceptor {
    private UserService userService;

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        String query = request.getURI().getQuery();
        String[] queries = StringUtils.split(query, "&");
        if (queries == null || queries.length == 0) {
            return false;
        }
        String token = null;
        for (String q : queries) {
            String[] qs = StringUtils.split(q, "=");
            if (qs != null && qs.length >= 2) {
                if ("token".equals(qs[0])) {
                    token = qs[1];
                }
            }
        }
        if (StringUtils.isEmpty(token)) {
            return false;
        }
        String username = userService.auth(token);
        if (StringUtils.isBlank(username)) {
            return false;
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        //do nothing
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
