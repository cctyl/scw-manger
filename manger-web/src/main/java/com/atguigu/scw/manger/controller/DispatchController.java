package com.atguigu.scw.manger.controller;

import com.atguigu.scw.manger.constant.MyConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 页面调度中心
 * 负责重定向到指定页面，并且查询指定页面的数据
 */
@Controller
public class DispatchController {



    @RequestMapping("/main.html")
    public String toMainPage(HttpSession session){

        Object loginUser = session.getAttribute(MyConstants.LOGIN_USER);
        if (loginUser==null){
            //没登录，不让访问
            return "redirect:/login.jsp";

        }else {
            //登录了，允许访问
            //查询出页面的数据放进去

            return "manager/main";
        }



    }




}


