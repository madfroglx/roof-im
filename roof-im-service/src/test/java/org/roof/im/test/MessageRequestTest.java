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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring.xml"})
public class MessageRequestTest extends MessageTest {
    @Test
    public void test() throws Exception {
        online("bcd");
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
}
