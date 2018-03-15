package org.roof.im.chain;

import com.roof.chain.support.NodeResult;
import org.roof.im.request.CloseSessionRequest;
import org.roof.im.request.OpenSessionRequest;
import org.roof.im.request.Request;
import org.roof.im.request.StartSessionRequest;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;


/**
 * 请求验证Node
 *
 * @author liuxin
 * @date 2018/3/5
 */
public class RequestValidNode {
    /**
     * 验证成功
     */
    private static final String VALIDATE_SUCCESS = "validateSuccess";
    /**
     * 验证失败
     */
    private static final String VALIDATE_FAIL = "validateFail";
    private Validator validator;

    public NodeResult<String[]> doNode(Request request) {
        if (request instanceof OpenSessionRequest
                || request instanceof CloseSessionRequest
                || request instanceof StartSessionRequest) {
            return new NodeResult<>(VALIDATE_SUCCESS);
        }
        Set<ConstraintViolation<Request>> constraintViolations = validator.validate(request);
        String[] messages = new String[constraintViolations.size()];
        int i = 0;
        for (ConstraintViolation<Request> sv : constraintViolations) {
            messages[i] = sv.getPropertyPath() + sv.getMessage();
            i++;
        }
        if (messages.length == 0) {
            return new NodeResult<>(VALIDATE_SUCCESS);
        } else {
            NodeResult<String[]> result = new NodeResult<>(VALIDATE_FAIL);
            result.setData(messages);
            return result;
        }
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }
}
