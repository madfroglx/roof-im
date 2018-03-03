package org.roof.im;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by zhenglt on 2017/11/29.
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody String home(Model model, HttpServletRequest request) {
        try {
            String addr = InetAddress.getLocalHost().getHostAddress();
            String port = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
            return "Hello IM，your ipaddress is : ip=" + addr+",prot:"+request.getLocalPort();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "Hello im";
    }
}
