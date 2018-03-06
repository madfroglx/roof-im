package org.roof.im.chain;

import com.roof.chain.support.NodeResult;
import org.apache.commons.lang3.StringUtils;
import org.roof.im.request.MessageRequest;
import org.roof.im.request.Request;

/**
 * @author liuxin
 * @date 2018/3/5
 */
public class RequestFormatNode {
    public String doNode(Request request) {
        return NodeResult.SUCCESS;
    }
}
