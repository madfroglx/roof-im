package org.roof.im.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.roof.chain.api.Chain;
import com.roof.chain.api.ValueStack;
import com.roof.chain.support.GenericValueStack;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.roof.im.chain.ImConstant;
import org.roof.im.request.CloseSessionRequest;
import org.roof.im.request.RequestType;
import org.roof.im.session.Session;
import org.roof.im.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring-im.xml"})
public class CloseSessionTest {
    private Chain enterChain;
    private SessionManager sessionManager;

    @Test
    public void testSessionNotExists() throws Exception {
        CloseSessionRequest request = new CloseSessionRequest();
        request.setSessionId(123);
        request.setRequestType(RequestType.closeSession.name());
        request.setToken("abc");

        ValueStack valueStack = getValueStack(request);
        enterChain.doChain(valueStack);
    }

    private ValueStack getValueStack(CloseSessionRequest request) {
        ValueStack valueStack = new GenericValueStack();
        valueStack.set(ImConstant.TEXT_MESSAGE, JSON.toJSONString(request));
        JSONObject jsonObjectMessage = JSON.parseObject(JSON.toJSONString(request));
        valueStack.set(ImConstant.JSON_OBJECT_MESSAGE, jsonObjectMessage);
        return valueStack;
    }

    @Test
    public void testSuccess() throws Exception {
        Session session = sessionManager.open("abc", "efg", 0, 99);

        CloseSessionRequest request = new CloseSessionRequest();
        request.setSessionId(session.getId());
        request.setRequestType(RequestType.closeSession.name());
        request.setToken("abc");

        ValueStack valueStack = getValueStack(request);
        enterChain.doChain(valueStack);
    }

    @Autowired
    public void setEnterChain(@Qualifier("enterChain") Chain enterChain) {
        this.enterChain = enterChain;
    }

    @Autowired
    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }
}
