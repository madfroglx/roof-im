package org.roof.im.chain.handler.impl;

import com.roof.chain.support.NodeResult;
import org.roof.im.request.HeartRequest;
import org.roof.im.user.UserStateService;

/**
 * 处理心跳请求
 *
 * @author liuxin
 * @date 2018/3/8
 */
public class HeartRequestHandlerNode {
    private UserStateService userStateService;

    public NodeResult<HeartRequest> doNode(HeartRequest request) {
        NodeResult<HeartRequest> nodeResult = new NodeResult<>(NodeResult.SUCCESS);
        request.setConnectId(null);
        request.setServerName(null);
        nodeResult.setData(request);
        userStateService.getStates(request.getUsername());
        return nodeResult;
    }

    public void setUserStateService(UserStateService userStateService) {
        this.userStateService = userStateService;
    }
}
