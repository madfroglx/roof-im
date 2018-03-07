package org.roof.im;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.roof.chain.api.Chain;
import com.roof.chain.api.ValueStack;
import com.roof.chain.support.GenericValueStack;
import org.roof.im.chain.ImConstant;
import org.roof.im.response.Response;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author hzliuxin1
 * @since 2018/3/7 0007
 */
public class AbstractChainController {
    protected Chain enterChain;

    protected Object doChain(@RequestBody String request, Logger logger) {
        ValueStack valueStack = new GenericValueStack();
        valueStack.set(ImConstant.TEXT_MESSAGE, request);
        JSONObject jsonObjectMessage;
        try {
            jsonObjectMessage = JSON.parseObject(request);
        } catch (Exception e) {
            logger.error("input json error: {}", request);
            return new Response<>(Response.ERROR, "json format error");
        }
        valueStack.set(ImConstant.JSON_OBJECT_MESSAGE, jsonObjectMessage);
        try {
            enterChain.doChain(valueStack);
            return valueStack.get(ImConstant.RESPONSE);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Response<>(Response.ERROR, e.getMessage());
        }
    }

    @Autowired
    public void setEnterChain(@Qualifier("enterChain") Chain enterChain) {
        this.enterChain = enterChain;
    }
}
