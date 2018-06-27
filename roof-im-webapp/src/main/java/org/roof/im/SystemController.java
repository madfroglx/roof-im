package org.roof.im;

import com.roof.chain.api.Chain;
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
 * @since 2018/6/25
 */
@Controller
@RequestMapping("/system")
public class SystemController extends AbstractChainController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SystemController.class);
    private Chain systemMessageChain;

    @RequestMapping(value = "/message")
    public @ResponseBody
    Object message(@RequestBody String request) {
        return doChain(request, LOGGER, systemMessageChain);
    }

    @Autowired
    public void setSystemMessageChain(@Qualifier("systemMessageChain") Chain systemMessageChain) {
        this.systemMessageChain = systemMessageChain;
    }
}
