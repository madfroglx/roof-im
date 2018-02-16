package org.roof.im.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.roof.chain.api.Chain;
import com.roof.chain.api.ValueStack;
import com.roof.chain.support.GenericValueStack;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.roof.im.chain.ImConstant;
import org.roof.im.request.OpenSessionRequest;
import org.roof.im.request.RequestType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring-im.xml"})
public class OpenSessionTest {
    private Chain enterChain;

    @Test
    public void testSuccess() throws Exception {
        OpenSessionRequest openSessionRequest = new OpenSessionRequest();
        openSessionRequest.setSender("abc");
        openSessionRequest.setReceiver("def");
        openSessionRequest.setToken("ab");
        openSessionRequest.setRequestType(RequestType.openSession.name());

        ValueStack valueStack = new GenericValueStack();
        valueStack.set(ImConstant.TEXT_MESSAGE, JSON.toJSONString(openSessionRequest));
        JSONObject jsonObjectMessage = JSON.parseObject(JSON.toJSONString(openSessionRequest));
        valueStack.set(ImConstant.JSON_OBJECT_MESSAGE, jsonObjectMessage);

        enterChain.doChain(valueStack);

    }

    @Autowired
    public void setEnterChain(@Qualifier("enterChain") Chain enterChain) {
        this.enterChain = enterChain;
    }
}
