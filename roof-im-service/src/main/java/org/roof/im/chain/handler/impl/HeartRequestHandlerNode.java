package org.roof.im.chain.handler.impl;

import com.roof.chain.support.NodeResult;
import org.roof.im.request.HeartRequest;

/**
 * 处理心跳请求
 *
 * @author liuxin
 * @date 2018/3/8
 */
public class HeartRequestHandlerNode {

    public NodeResult<HeartRequest> doNode(HeartRequest request) {
        NodeResult<HeartRequest> nodeResult = new NodeResult<>(NodeResult.SUCCESS);
        nodeResult.setData(request);
        return nodeResult;
    }
}
