package com.blog.util;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {
    public static String formatLike(String str){
        if (isNotEmpty(str)){
            return "%"+str+"%";
        }else {
            return null;
        }
    }


    /*
    判断字符串是否不为空     用于删除字符串前后空格---str.trim()
     */
    public static boolean isNotEmpty(String str){
        if (str != null && !"".equals(str.trim())){
            return true;
        }
        return false;
    }
    /*
    判断字符串是否为空     用于删除字符串前后空格---str.trim()
     */
    public static boolean isEmpty(String str){
        if (str == null || "".equals(str.trim())){
            return true;
        }
        return false;
    }

    /**
     *
     *
     */
    public static List<String> fileterWhite(List<String> list){
        ArrayList<String> resultList = new ArrayList<>();
        for (String l:list){
            if (isNotEmpty(l)){
                resultList.add(l);
            }
        }
        return resultList;
    }
}
