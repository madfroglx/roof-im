package org.roof.im.chain.handler.impl;

import com.roof.chain.api.ValueStack;
import org.apache.commons.lang3.StringUtils;
import org.roof.im.chain.handler.AbstractRequestHandlerNode;
import org.roof.im.request.OfflineRequest;
import org.roof.im.transport.ServerNameBuilder;
import org.roof.im.user.UserState;

import java.util.Iterator;
import java.util.List;

/**
 * 下线处理
 */
public class OfflineHandlerNode extends AbstractRequestHandlerNode<OfflineRequest> {
    private ServerNameBuilder serverNameBuilder;
    private static final String USER_NOT_ONLINE = "userNotOnline";
    private static final String OFFLINE_SUCCESS = "offlineSuccess";

    @Override
    public String doNode(OfflineRequest request, ValueStack valueStack) {
        String username = request.getUsername();
        String serverName = serverNameBuilder.getName();
        List<UserState> userStates = userStateService.getStates(username);
        if (userStates == null || userStates.size() == 0) {
            return USER_NOT_ONLINE;
        }
        for (Iterator<UserState> iterator = userStates.iterator(); iterator.hasNext(); ) {
            UserState next = iterator.next();
            if (StringUtils.equals(next.getServerName(), serverName)) {
                iterator.remove();
                if (userStates.size() == 0) {
                    userStateService.offline(username);
                } else {
                    userStateService.online(username, userStates);
                }
                return OFFLINE_SUCCESS;
            }
        }
        return USER_NOT_ONLINE;
    }

    public void setServerNameBuilder(ServerNameBuilder serverNameBuilder) {
        this.serverNameBuilder = serverNameBuilder;
    }
}
