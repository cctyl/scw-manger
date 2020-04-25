package com.atguigu.scw.manger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class RedirectController {

    @RequestMapping("/red")
    public String redirectPage(HttpSession session){
        String url = (String)session.getAttribute("url");
        if (url!=null){
            session.removeAttribute("url");
            return url;
        }else {

            return "redirect:/index.jsp";
        }

    }
}
