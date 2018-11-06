package cn.linguolai.template.service.imp;

import cn.linguolai.template.bean.User;
import cn.linguolai.template.json.JsonDate;
import cn.linguolai.template.mapper.UserMapper;
import cn.linguolai.template.service.LoginService;
import cn.linguolai.template.utils.base64.Base64Utils;
import cn.linguolai.template.utils.rsa.RSAUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

@Transactional
@Service
public class LoginServiceImpl implements LoginService {


    @Resource
    private UserMapper userMapper;

    /**
     * 登录验证 返回登录验证后的JsonDate对象，携带登录成功或者失败信息
     * date.ret = true 表示登录成功
     * date.message表示携带的信息
     * @param user 登陆者信息
     * @param vcode 登录验证码
     * @return
     */
    public JsonDate login(User user,String vcode) throws Exception {
        //验证user完整性
        if (!check(user).isRet()) {
            return check(user);
        }

        //创建返回json数据
        JsonDate data;

        //获取主体,主体提交请求
        Subject subject = SecurityUtils.getSubject();
        //获取验证码
        String verifyCode = (String) subject.getSession().getAttribute("verifyCode");
        //验证验证码
        if (vcode == null || !vcode.equalsIgnoreCase(verifyCode)) {
            data = new JsonDate(false, "验证码输入错误!请重试!");
            return data;
        }

                //获取密钥
        String privateKey = (String) subject.getSession().getAttribute(RSAUtils.PRIVATE_KEY);
        if (privateKey == null) {
            data = new JsonDate(false, "安全数据丢失!无法验证数据正确性!");
            return data;
        }
        //将密文解码,并重新赋值给user
        String password = new String(RSAUtils.decryptByPrivateKey(Base64Utils.decode(user.getPassword()), privateKey));
        user.setPassword(password);

        //创建token
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());

        //提交登录
        try {
            subject.login(token);
        }catch (UnknownAccountException e) {
            System.err.println(e.getMessage());
            data = new JsonDate(false, "登录失败,用户名不存在!");
            return data;
        }catch (IncorrectCredentialsException e) {
            System.err.println(e.getMessage());
            data = new JsonDate(false, "登录失败,密码不正确!");
            return data;
        } catch (AuthenticationException e) {
            System.err.println(e.getMessage());
            data = new JsonDate(false, "登录失败!");
            return data;
        }

        //执行到这里说明登录成功
        data = new JsonDate(true, "登录成功!");
        return data;
    }

    /**
     * 生成密钥对,返回公钥,把私钥保存到session中
     *
     * @return 公钥
     */
    public JsonDate getPublicKey() throws Exception {
        //创建密钥对
        Map<String, Object> keyMap = RSAUtils.genKeyPair();
        String publicKey = RSAUtils.getPublicKey(keyMap);
        String privateKey = RSAUtils.getPrivateKey(keyMap);

        //创建返回的json数据
        JsonDate date;

        if (publicKey == null || privateKey == null) {
            date = new JsonDate(false, "安全数据创建失败!,无法保证数据安全!");
            return date;
        }

        //获取请求主体
        Subject subject = SecurityUtils.getSubject();

        //讲私钥保存到session中
        subject.getSession().setAttribute(RSAUtils.PRIVATE_KEY, privateKey);

        //返回公钥
        date = new JsonDate(true);
        date.setObject(publicKey);
        return date;
    }


    /**
     * 验证user的数据完整性
     * @return 验证后结果
     */
    private JsonDate check(User user) {

        JsonDate date;
        if (user == null) {
            date = new JsonDate(false, "登录信息异常!");
        }else if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            date = new JsonDate(false, "请输入用户名!");
        } else if (user.getPassword() == null || user.getPassword().isEmpty()) {
            date = new JsonDate(false, "请输入密码!");
        } else {
            date = new JsonDate(true);
        }

        return date;
    }
}
