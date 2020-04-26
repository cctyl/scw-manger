package com.atguigu.scw.manger.controller;

import com.atguigu.scw.manger.constant.MyConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class RedirectController {

    /**
     * 用于解决表单重复提交的问题
     * 相比老师的，复用性提高了
     * @param session
     * @return
     */
    @RequestMapping("/url")
    public String redirectPage(HttpSession session){
        String url = (String)session.getAttribute("url");
        if (url!=null){
            //没有必要去除了，因为下次如果还会用到转发，那么url属性会被覆盖
            //如果他没有登录，那么显然session为空，也是拿不到数据，也不会转发到这里
            //session.removeAttribute("url");
            return url;
        }else {
            if(session.getAttribute(MyConstants.LOGIN_USER)==null){
                //没有登录，直接去登录页面
                return "redirect:/login.jsp";

            }else {
                //虽然登录了,但是session域中没有url属性，不知道要去哪里，就直接去main.jsp吧
                return "manager/main";

            }
        }

    }
}
