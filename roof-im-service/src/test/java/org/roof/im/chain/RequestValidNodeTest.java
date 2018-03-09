package org.roof.im.chain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.roof.im.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import java.util.Set;

import static org.junit.Assert.*;

/**
 * @author liuxin
 * @date 2018/3/9
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring-validator.xml"})
public class RequestValidNodeTest {
    private Validator validator;
    private RequestValidNode requestValidNode;

    @Test
    public void doNode() {
//        requestValidNode = new RequestValidNode();
        Request request = new Request();
//        requestValidNode.doNode(request);
        Set<ConstraintViolation<Request>> constraintViolations = validator.validate(request);
        for (ConstraintViolation<Request> sv : constraintViolations) {
            //此处输出:{key}
            System.out.println(sv.getPropertyPath());
            //此处输出:key对应的消息
            System.out.println(sv.getMessage());

            //sv.getPropertyPath()显示的是校验失败的属性名
        }
    }

    @Autowired
    public void setValidator(Validator validator) {
        this.validator = validator;
    }
}