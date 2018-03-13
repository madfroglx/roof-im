package org.roof.im.chain;

import com.roof.chain.support.NodeResult;
import org.apache.commons.lang3.StringUtils;
import org.roof.im.request.Request;
import org.roof.im.user.OnlineUserStore;

/**
 * @author hzliuxin1
 * @since 2018/3/13 0013
 */
public class OfflineUserRemoveCacheNode {

    private OnlineUserStore onlineUserStore;

    public String doNode(Request request) {
        String username = request.getUsername();
        if (StringUtils.isNotBlank(username)) {
            onlineUserStore.remove(username);
        }
        return NodeResult.SUCCESS;
    }

    public void setOnlineUserStore(OnlineUserStore onlineUserStore) {
        this.onlineUserStore = onlineUserStore;
    }
}
