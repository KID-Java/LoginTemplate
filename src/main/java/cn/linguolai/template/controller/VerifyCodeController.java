package cn.linguolai.template.controller;

import cn.linguolai.template.utils.vcode.GifVerifyCode;
import cn.linguolai.template.utils.vcode.VerifyCode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Controller
public class VerifyCodeController {

    /**
     * 获取验证码并保存到session中
     * @param request
     * @param response
     */
    @RequestMapping("/vcode")
    public void getVerifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /**
         * 设置不缓存
         */
        response.setHeader("Pragma", "No-cache");
        response.setHeader("ShiroRedisCache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        //设置mime类型为图片
        response.setContentType("image/jpeg");

        //创建一个gif验证码图片
        VerifyCode verifyCode = new GifVerifyCode(120, 45, 4);

        //响应gif验证码图片给前台
        OutputStream outputStream = response.getOutputStream();
        verifyCode.out(outputStream);
        //把验证码保存到session中
        Subject subject = SecurityUtils.getSubject();
        subject.getSession().setAttribute("verifyCode", verifyCode.text());
        outputStream.flush();
    }
}
