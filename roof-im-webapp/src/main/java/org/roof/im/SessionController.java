package org.roof.im;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.roof.chain.api.Chain;
import com.roof.chain.api.ValueStack;
import com.roof.chain.support.GenericValueStack;
import org.roof.im.chain.ImConstant;
import org.roof.im.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author liuxin
 */
@Controller
@RequestMapping("/session")
public class SessionController extends AbstractChainController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionController.class);

    /**
     * 打开session
     *
     * @param request 打开session请求
     * @return
     */
    @RequestMapping(value = "/open")
    public @ResponseBody
    Object open(@RequestBody String request) {
        return doChain(request, LOGGER);
    }

    /**
     * 关闭session
     *
     * @param request 关闭session请求
     * @return
     */
    @RequestMapping(value = "/close")
    public @ResponseBody
    Object close(@RequestBody String request) {
        return doChain(request, LOGGER);
    }

    @Autowired
    public void setEnterChain(@Qualifier("enterChain") Chain enterChain) {
        this.enterChain = enterChain;
    }

}
