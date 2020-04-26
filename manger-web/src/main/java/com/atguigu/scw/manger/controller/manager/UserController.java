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
    //相对路径，相对于src\main\webapp\WEB-INF\jsps
    private final  String MANAGER_MAIN = "manager/main";

    @Autowired
    UserService userService;

    /**
     * 注册用户
     * @param user
     * @return
     */
    @RequestMapping("/reg")
    public String reg(TUser user,  HttpSession session){




        //1.把用户的数据存储到数据库
       boolean flag =  userService.register(user);

       //2.判断注册结果  2225385 2237998 2233289 22220452668611 2238778 2222363
        if (flag){
            //注册成功，返回控制面板
            //将已经登录的用户放到session中, key使用的常量
            session.setAttribute(MyConstants.LOGIN_USER,user);
            //为了防止页面重复提交，那么我们就对他进行重定向

            //删除可能存在的错误提示信息
            session.removeAttribute("regError");
            return "redirect:/main.html";
        }else {
            //注册失败，返回注册页面
            //添加错误提示信息
            session.setAttribute("regError","用户名重复");

            //想将用户输入的数据再次放回输入框，这里不用再将user存入域中
            //因为controller拿到的user本身就是从隐含模型中拿，现在到页面上，只需使用  ${TUser} 就能拿到.(一般用类名首字母小写，但是这个类开头两个字母都大写，因此需要换一种写法)

            //因reg.jsp不在jsps目录下，所以用forward转发
            return "redirect:/reg.jsp";
        }



    }



    /**
     * 用户登录
     * @param user
     * @return
     */
    @RequestMapping("/login")
    public String login(TUser user,HttpSession session){

        TUser loginUser = userService.login(user);
        if (loginUser==null){
            //登录失败
            //将错误提示展示到登录页面，用重定向就不能放request域中了，放在session中
            session.setAttribute("errorUser",user);
            session.setAttribute("msg","登录失败，用户名或者密码错误");

            //绝对路径转发,/表示http://localhost/
            // 因为没有用到视图解析器，所以这里写全名称

            //用forward，url显示的是http://localhost:8080/manger_web_war_exploded/permission/user/login
            // return "forward:/login.jsp";
            //用redirect，显示的是：http://localhost:8080/manger_web_war_exploded/login.jsp    ，显然是这个好
            return "redirect:/login.jsp";

        }else {

            //登录成功，把错误消息从session中移除
            session.removeAttribute("errorUser");
            session.removeAttribute("msg");

            //给session域中添加用户对象,注意这里放的是从数据库查出来的，而不是前端提交的
            session.setAttribute(MyConstants.LOGIN_USER,loginUser);

            //去页面调度中心，实现重定向
            return "redirect:/main.html";
        }



    }
}
