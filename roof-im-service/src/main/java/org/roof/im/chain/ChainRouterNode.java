package org.roof.im.chain;

import com.roof.chain.api.Chain;
import com.roof.chain.api.ValueStack;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * 根据不同的消息类型，将消息路由到对应的处理Chain
 */
public class ChainRouterNode {
    private String valueStackKey;
    private Map<String, Chain> mapping;
    private static final String ROUTE_SUCCESS = "routeSuccess";
    private static final String ROUTE_FAIL = "routeFail";

    public Object doNode(ValueStack valueStack) throws Exception {
        Assert.notNull(valueStack, "ValueStack cannot null");
        String chainName = valueStack.getAsString(valueStackKey);
        Assert.hasText(chainName, "ValueStack cannot found key " + valueStackKey);
        Chain chain = mapping.get(chainName);
        Assert.notNull(chain, "Chain mapping cannot key " + chainName);
        return chain.doChain(valueStack);
    }

    public String getValueStackKey() {
        return valueStackKey;
    }

    public void setValueStackKey(String valueStackKey) {
        this.valueStackKey = valueStackKey;
    }

    public Map<String, Chain> getMapping() {
        return mapping;
    }

    public void setMapping(Map<String, Chain> mapping) {
        this.mapping = mapping;
    }
}
