package com.atguigu.scw.manger.controller.manager;


import com.atguigu.scw.manger.bean.TUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/permission/user/*")
@Controller
public class UserController {

    /**
     * 注册用户
     * @param user
     * @return
     */
    @RequestMapping("/reg")
    public String reg(TUser user){

        System.out.println("用户注册");
        return "manager/main";
    }
}
