package com.atguigu.scw.manger.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyStringUtils {

    private static  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 拿到当前时间的字符串
     * @return
     */
    public static String getTimeStr(){
        return dateFormat.format(new Date());
    }

    /**
     * 重载方法，用于已有时间，需要将他转换成字符串
     * @param date
     * @return
     */
    public static String getTimeStr(Date date){

        return dateFormat.format(date);
    }
}
