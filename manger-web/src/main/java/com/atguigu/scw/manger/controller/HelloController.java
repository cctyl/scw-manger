package com.atguigu.scw.manger.controller;

import com.atguigu.scw.manger.bean.TUser;
import com.atguigu.scw.manger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @Autowired
    UserService userService;


    @RequestMapping("/hello")
    public String hello(@RequestParam("id") Integer id, Model model) {


        TUser userById = userService.getUserById(id);
        model.addAttribute("user", userById);
        return "forward:/success.jsp";
    }


    public String index() {

        return "index";
    }
}
