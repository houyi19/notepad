package com.study.notepad.util;

import java.text.SimpleDateFormat;
import java.util.Date;

//负责转换系统时间到日期的工具
public class DateUtil {

    //    转换成日期时间yyyy-MM-dd HH:mm:ss;
    public static String timeStamp2Date(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds + "000")));
    }
}
