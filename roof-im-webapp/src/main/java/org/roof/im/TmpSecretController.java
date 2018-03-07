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
 * @author hzliuxin1
 * @since 2018/3/7 0007
 */
@Controller
@RequestMapping("/tmpSecret")
public class TmpSecretController extends AbstractChainController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TmpSecretController.class);

    @RequestMapping("/get")
    public @ResponseBody
    Object get(@RequestBody String request) {
        return doChain(request, LOGGER);
    }

    @Autowired
    public void setEnterChain(@Qualifier("enterChain") Chain enterChain) {
        this.enterChain = enterChain;
    }
}
