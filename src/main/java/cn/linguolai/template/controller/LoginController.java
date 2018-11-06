package cn.linguolai.template.controller;

import cn.linguolai.template.bean.User;
import cn.linguolai.template.json.JsonDate;
import cn.linguolai.template.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


@Controller
public class LoginController {

    @Resource
    private LoginService loginService;


    /**
     * 登录
     *
     * @param user 封装的登录用户信息
     * @return 返回的json描述数据
     */
    @RequestMapping(value = "/subLogin", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public JsonDate login(User user, String verifyCode) throws Exception {
        return loginService.login(user,verifyCode);

    }


    /**
     * 生成密钥对,返回公钥,把私钥保存到session中
     *
     * @return 公钥
     */
    @RequestMapping(value = "/getPubKey", method = RequestMethod.POST)
    @ResponseBody
    public JsonDate getPublicKey() throws Exception {
        return loginService.getPublicKey();
    }


}
