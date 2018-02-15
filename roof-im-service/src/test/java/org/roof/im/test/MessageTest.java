package org.roof.im.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.roof.chain.api.ValueStack;
import com.roof.chain.support.GenericValueStack;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.roof.im.chain.ImConstant;
import org.roof.im.request.ContentType;
import org.roof.im.request.MessageRequest;
import org.roof.im.transport.ServerNameBuilder;
import org.roof.im.user.UserStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring.xml"})
public class MessageTest extends RequestTest {
    private UserStateService userStateService;
    private ServerNameBuilder serverNameBuilder;

    @Test
    public void testSuccess() throws Exception {
        online("bcd");
        online("abc");
        MessageRequest messageRequest = new MessageRequest();
        messageRequest.setRequestType("message");
        messageRequest.setToken("abc");
        messageRequest.setReceiver("bcd");
        messageRequest.setType(ContentType.TXT);
        messageRequest.setClientType("h5");
        messageRequest.setCreateTime(System.currentTimeMillis());
        messageRequest.setPayload("test");

        ValueStack valueStack = new GenericValueStack();
        valueStack.set(ImConstant.TEXT_MESSAGE, JSON.toJSONString(messageRequest));
        valueStack.set(ImConstant.CONNECT_ID, "1");
        JSONObject jsonObjectMessage = JSON.parseObject(JSON.toJSONString(messageRequest));
        valueStack.set(ImConstant.JSON_OBJECT_MESSAGE, jsonObjectMessage);

        enterChain.doChain(valueStack);

    }

    @Autowired
    public void setUserStateService(UserStateService userStateService) {
        this.userStateService = userStateService;
    }

    @Autowired
    public void setServerNameBuilder(ServerNameBuilder serverNameBuilder) {
        this.serverNameBuilder = serverNameBuilder;
    }
}
