package org.roof.im.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.roof.chain.api.Chain;
import com.roof.chain.api.ValueStack;
import com.roof.chain.support.GenericValueStack;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.roof.im.chain.ImConstant;
import org.roof.im.request.OfflineRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring.xml"})
public class OfflineTest {
    private Chain enterChain;

    @Test
    public void testSuccess() throws Exception {
        OfflineRequest offlineRequest = new OfflineRequest();
        offlineRequest.setClientType("h5");
        offlineRequest.setRequestType("offline");
        offlineRequest.setToken("abc");
        offlineRequest.setCreateTime(System.currentTimeMillis());

        ValueStack valueStack = new GenericValueStack();
        valueStack.set(ImConstant.TEXT_MESSAGE, JSON.toJSONString(offlineRequest));
        valueStack.set(ImConstant.CONNECT_ID, "1");
        JSONObject jsonObjectMessage = JSON.parseObject(JSON.toJSONString(offlineRequest));
        valueStack.set(ImConstant.JSON_OBJECT_MESSAGE, jsonObjectMessage);
        enterChain.doChain(valueStack);
    }

    @Autowired
    public void setEnterChain(@Qualifier("enterChain") Chain enterChain) {
        this.enterChain = enterChain;
    }
}
