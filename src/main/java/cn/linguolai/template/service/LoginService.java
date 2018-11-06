package cn.linguolai.template.service;

import cn.linguolai.template.bean.User;
import cn.linguolai.template.json.JsonDate;

public interface LoginService {

    JsonDate login(User user, String vcode) throws Exception;

    JsonDate getPublicKey() throws Exception;


}
