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
import org.roof.im.transport.ServerNameBuilder;
import org.roof.im.user.UserState;
import org.roof.im.user.UserStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring-im.xml"})
public class OfflineTest {
    private Chain enterChain;

    private UserStateService userStateService;
    private ServerNameBuilder serverNameBuilder;
    @Test
    public void testUserNotOnline() throws Exception {
        OfflineRequest offlineRequest = createRequest();
        ValueStack valueStack = createValueStack(offlineRequest);
        enterChain.doChain(valueStack);
    }

    private ValueStack createValueStack(OfflineRequest offlineRequest) {
        ValueStack valueStack = new GenericValueStack();
        valueStack.set(ImConstant.TEXT_MESSAGE, JSON.toJSONString(offlineRequest));
        valueStack.set(ImConstant.CONNECT_ID, "1");
        JSONObject jsonObjectMessage = JSON.parseObject(JSON.toJSONString(offlineRequest));
        valueStack.set(ImConstant.JSON_OBJECT_MESSAGE, jsonObjectMessage);
        return valueStack;
    }

    private OfflineRequest createRequest() {
        OfflineRequest offlineRequest = new OfflineRequest();
        offlineRequest.setClientType("h5");
        offlineRequest.setRequestType("offline");
        offlineRequest.setToken("abc");
        offlineRequest.setCreateTime(System.currentTimeMillis());
        System.out.println(JSON.toJSONString(offlineRequest));
        return offlineRequest;
    }

    @Test
    public void testSuccess() throws Exception {
        List<UserState> states = new ArrayList<>();
        UserState userState = new UserState();
        userState.setConnectId("1");
        userState.setClientType("h5");
        userState.setUsername("abc");
        userState.setServerName(serverNameBuilder.getName());
        states.add(userState);

        UserState userState2 = new UserState();
        userState2.setConnectId("1");
        userState2.setClientType("applet");
        userState2.setUsername("abc");
        userState2.setServerName(serverNameBuilder.getName());
        states.add(userState2);

        userStateService.online("abc", states);
        OfflineRequest offlineRequest = createRequest();
        ValueStack valueStack = createValueStack(offlineRequest);
        enterChain.doChain(valueStack);
    }

    @Autowired
    public void setEnterChain(@Qualifier("enterChain") Chain enterChain) {
        this.enterChain = enterChain;
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
