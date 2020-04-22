package com.atguigu.scw.manger.controller.manager;


import com.atguigu.scw.manger.bean.TUser;
import com.atguigu.scw.manger.constant.MyConstants;
import com.atguigu.scw.manger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@RequestMapping("/permission/user/*")
@Controller
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 注册用户
     * @param user
     * @return
     */
    @RequestMapping("/reg")
    public String reg(TUser user, Model model, HttpSession session){




        //1..把用户的数据存储到数据库
       boolean flag =  userService.register(user);

       //2.判断注册结果
        if (flag){
            //注册成功，返回控制面板
            //将已经登录的用户放到session中, key使用的常量
            session.setAttribute(MyConstants.LOGIN_USER,user);
            return "manager/main";
        }else {
            //注册失败，返回注册页面
            //添加错误提示信息
            model.addAttribute("regError","用户名重复");

            //想将用户输入的数据再次放回输入框，这里不用再将user存入域中
            //因为controller拿到的user本身就是从隐含模型中拿，现在到页面上，只需使用  ${TUser} 就能拿到.(一般用类名首字母小写，但是这个类开头两个字母都大写，因此需要换一种写法)

            //因reg.jsp不在jsps目录下，所以用forward转发
            return "forward:/reg.jsp";
        }



    }
}
