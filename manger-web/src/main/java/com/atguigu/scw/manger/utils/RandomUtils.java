package com.atguigu.scw.manger.utils;

import java.util.Random;

public class RandomUtils {

    /**
     * 随机生成一个4位数的验证码
     * @return
     */
    public static  String getRandomCode(){


        String str="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb=new StringBuilder(4);
        for(int i=0;i<4;i++)
        {
            char ch=str.charAt(new Random().nextInt(str.length()));
            sb.append(ch);
        }
        return sb.toString();

    }
}
