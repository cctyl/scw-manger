package com.atguigu.scw.manger.utils;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class MailUtils {

    /**
     * 发送邮件
     * @param to
     * @param msg
     */
    public static void sendMail(String to,String title ,String msg)  {

        SimpleEmail email = new SimpleEmail();
        //设置邮件服务器
        email.setHostName("smtp.renlianren.com");
        //设置用户名和密码
        email.setAuthentication("test@renlianren.com","renlianren.123");
        //设置端口，不设置默认为25
        email.setSmtpPort(25);

        try {
            //设置目标地址
            email.addTo(to);
            //设置发送人
            //不能写成大厂的邮箱地址，如果写成 163 qq 这些，那么发不到qq邮箱，因为会被当成垃圾邮件拦截
            //写成一些不知名的域名就可以发送
            email.setFrom("rlr1232@tyl.cn");
            //邮件主题
            email.setSubject(title);
            //设置邮件内容
            email.setMsg(msg);

            //邮件发送
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}
