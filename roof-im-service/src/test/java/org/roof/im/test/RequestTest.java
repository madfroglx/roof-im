package org.roof.im.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.roof.chain.api.Chain;
import com.roof.chain.api.ValueStack;
import com.roof.chain.support.GenericValueStack;
import org.roof.im.chain.ImConstant;
import org.roof.im.request.ClientType;
import org.roof.im.request.UserStateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class RequestTest {
    protected Chain enterChain;

    public void online(String token) throws Exception {
        UserStateRequest userStateRequest = new UserStateRequest();
        userStateRequest.setClientType(ClientType.h5.name());
        userStateRequest.setRequestType("online");
        userStateRequest.setToken(token);
        ValueStack valueStack = new GenericValueStack();
        System.out.println(JSON.toJSONString(userStateRequest));
        valueStack.set(ImConstant.TEXT_MESSAGE, JSON.toJSONString(userStateRequest));
        valueStack.set(ImConstant.CONNECT_ID, "1");
        JSONObject jsonObjectMessage = JSON.parseObject(JSON.toJSONString(userStateRequest));
        valueStack.set(ImConstant.JSON_OBJECT_MESSAGE, jsonObjectMessage);
        enterChain.doChain(valueStack);
    }

    @Autowired
    public void setEnterChain(@Qualifier("enterChain") Chain enterChain) {
        this.enterChain = enterChain;
    }
}
