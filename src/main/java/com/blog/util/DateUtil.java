package com.blog.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 */
public class DateUtil {
    /**
     * 得到当前秒的时间字符串
     */
    public static String getCurrentDateStr() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        return simpleDateFormat.format(date);
    }

    /**
     * 时间格式转换
     *
     * @return
     */
    public static String formatDate(Date date, String format) {
        String result = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        if (date != null) {
            result = simpleDateFormat.format(date);
        }
        return result;
    }
}
