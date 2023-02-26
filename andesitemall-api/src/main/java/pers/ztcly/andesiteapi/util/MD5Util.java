package pers.ztcly.andesiteapi.util;

import org.springframework.util.DigestUtils;

/**
 * @author ztcly
 * @date 2023-02-24 19:46
 * @description
 **/
public class MD5Util {
    //盐，用于混交md5
    private static final String slat = "&%5124***&&%%$$#@";

    public static String getMD5(String str) {
        String base = str +"/"+slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
}
