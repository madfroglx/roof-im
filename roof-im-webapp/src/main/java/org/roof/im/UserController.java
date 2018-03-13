package org.roof.im;

import com.roof.chain.support.NodeResult;
import org.roof.im.response.Response;
import org.roof.im.user.OnlineUserStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hzliuxin1
 * @since 2018/3/13 0013
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private OnlineUserStore onlineUserStore;

    /**
     * 获取当前在线用户
     *
     * @return
     */
    @RequestMapping(value = "/queryOnline")
    public @ResponseBody
    Object queryOnline() {
        Response<String[]> response = new Response<>(NodeResult.SUCCESS);
        response.setResult(onlineUserStore.get());
        return response;
    }

    @Autowired
    public void setOnlineUserStore(OnlineUserStore onlineUserStore) {
        this.onlineUserStore = onlineUserStore;
    }
}
