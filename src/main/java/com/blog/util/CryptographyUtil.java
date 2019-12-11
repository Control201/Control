package com.blog.util;

import org.apache.shiro.crypto.hash.Md5Hash;

public class CryptographyUtil {

    /**
     * md5加密
     * @param str 加密字符创
     * @param salt 密钥（盐）
     * @return
     */
    public static String md5(String str,String salt){
        return new Md5Hash(str,salt).toString();
    }

    public static void main(String[] args) {
        System.out.println(md5("123","salt"));
    }
}
