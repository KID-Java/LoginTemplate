package cn.linguolai.template.utils.string;

import java.util.UUID;

/**
 * 字符串工具类
 */
public class StringUtils {

    public static String getUuid() {
        return UUID.randomUUID().toString().trim().replace("-", "");
    }

}
