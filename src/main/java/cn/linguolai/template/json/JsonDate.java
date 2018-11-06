package cn.linguolai.template.json;

import java.io.Serializable;

/**
 * 用于给前段返回json数据
 */

public class JsonDate implements Serializable {

    //成功或失败的标志
    private boolean ret;
    //返回的字符串数据
    private String message;
    //返回的对象
    private Object object;


    public JsonDate(boolean ret) {
        this.ret = ret;
    }

    public JsonDate(boolean ret, String message) {
        this.ret = ret;
        this.message = message;
    }

    public JsonDate(boolean ret, String message, Object object) {
        this.ret = ret;
        this.message = message;
        this.object = object;
    }

    public JsonDate(boolean ret, Object object) {
        this.ret = ret;
        this.object = object;
    }

    public boolean isRet() {
        return ret;
    }

    public void setRet(boolean ret) {
        this.ret = ret;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
