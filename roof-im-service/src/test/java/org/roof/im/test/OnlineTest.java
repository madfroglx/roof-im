package org.roof.im.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring-im.xml"})
public class OnlineTest extends MessageTest {


    @Test
    public void testSuccess() throws Exception {
        online("abc");
    }

}
