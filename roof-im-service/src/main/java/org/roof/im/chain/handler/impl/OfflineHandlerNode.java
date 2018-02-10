package org.roof.im.chain.handler.impl;

import com.roof.chain.api.ValueStack;
import org.roof.im.chain.handler.AbstractRequestHandlerNode;
import org.roof.im.request.OfflineRequest;
import org.roof.im.route.ServerNameBuilder;
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
            if (next.getServerName().equals(serverName)) {
                iterator.remove();
                return OFFLINE_SUCCESS;
            }
        }
        return USER_NOT_ONLINE;
    }

    public void setServerNameBuilder(ServerNameBuilder serverNameBuilder) {
        this.serverNameBuilder = serverNameBuilder;
    }
}
