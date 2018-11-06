package cn.linguolai.template.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseController {

    /**
     * 去登录界面
     * @return
     */
    @RequestMapping("/login")
    public String tologin() {
        //假设这里处理了很多数据,然后返回到登录首页
        return "login/login";
    }

    /**
     * 去主页
     * @return
     */
    @RequestMapping("/home")
    public String tohome() {
        return "/home/home";
    }

    @RequiresRoles("admin")
    @RequestMapping("/admin")
    public String toadmin() {
        return "/home/admin";
    }
}
