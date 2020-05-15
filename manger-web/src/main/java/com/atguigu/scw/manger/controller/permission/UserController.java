package com.atguigu.scw.manger.controller.permission;


import com.atguigu.scw.manger.bean.Msg;
import com.atguigu.scw.manger.bean.TRole;
import com.atguigu.scw.manger.bean.TUser;
import com.atguigu.scw.manger.bean.TUserToken;
import com.atguigu.scw.manger.constant.MyConstants;
import com.atguigu.scw.manger.service.TRoleService;
import com.atguigu.scw.manger.service.TUserRoleService;
import com.atguigu.scw.manger.service.TUserTokenService;
import com.atguigu.scw.manger.service.UserService;
import com.atguigu.scw.manger.utils.CookieUtils;
import com.atguigu.scw.manger.utils.MailUtils;
import com.atguigu.scw.manger.utils.RandomUtils;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RequestMapping("/permission/user/*")
@Controller
public class UserController {
    //相对路径，相对于src\main\webapp\WEB-INF\jsps
    private final String MANAGER_MAIN = "manager/main";
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    UserService userService;

    @Autowired
    TRoleService roleService;

    @Autowired
    TUserRoleService userRoleService;

    @Autowired
    TUserTokenService tokenService;

    /**
     * 分配角色
     *
     * @param rids
     * @param uid
     * @param opt
     * @return
     */
    @RequestMapping("/assignRole")
    @ResponseBody
    public Msg assignRole(@RequestParam(name = "rids", required = true) String rids,
                          @RequestParam(name = "uid", required = true) Integer uid,
                          @RequestParam(name = "opt", required = true) String opt) {
        //去除空格
        opt = opt.trim();

        int i = 0;

        if (opt.equals("add")) {
            //添加角色
            logger.debug("------------添加角色----------");
            if (rids.contains("-")) {
                //多个角色添加
                String[] split = rids.split("-");

                for (String rid : split) {
                    i += userRoleService.addRole(Integer.parseInt(rid), uid);
                }
            } else {
                //单个角色添加
                i = userRoleService.addRole(Integer.parseInt(rids), uid);
            }

        } else if (opt.equals("del")) {
            //删除角色
            logger.debug("------------删除角色----------");

            if (rids.contains("-")) {
                //多个角色添加
                String[] split = rids.split("-");

                for (String rid : split) {
                    i += userRoleService.delRole(Integer.parseInt(rid), uid);
                }

            } else {
                //单个角色添加
                i = userRoleService.delRole(Integer.parseInt(rids), uid);
            }

        }


        if (i > 0) {
            return Msg.success();
        } else {
            return Msg.fail();
        }


    }


    /**
     * 来到权限分配页面，并且展示权限数据
     *
     * @param id
     * @return
     */
    @RequestMapping("/assignRole.html")
    public String toAssignRolePage(@RequestParam(value = "id", required = true) Integer id, Model model) {
        //查出用户没有的角色
        List<TRole> othersRole = roleService.findOthersRole(id);
        logger.debug("ordersRole-----" + othersRole.toString());
        //查出用户拥有的角色
        List<TRole> roleByUid = roleService.findRoleByUid(id);
        logger.debug("roleByUid------" + roleByUid.toString());

        model.addAttribute("unroles", othersRole);
        model.addAttribute("roles", roleByUid);

        return "manager/permission/assignRole";
    }


    /**
     * 去add.jsp页面，无他，只是为了安全，一点页面也不想被外人访问
     *
     * @return
     */
    @RequestMapping("/add.html")
    public String toAddPage() {

        return "manager/permission/user_add";
    }


    /**
     * 用户添加
     *
     * @param user
     * @param session
     * @return
     */
    @RequestMapping("/add")
    public String addUser(@Valid TUser user, HttpSession session) {

        boolean register = userService.register(user);

        if (register == true) {
            session.removeAttribute("errorMsg");
            return "redirect:/permission/user/list.html?search=" + user.getLoginacct();
        } else {
            //新增用户失败
            session.setAttribute("errorMsg", "用户名重复");
            return "redirect:/permission/user/add.html";
        }


    }


    /**
     * 修改用户数据
     *
     * @return
     */
    @RequestMapping("/update")
    public String updateUser(@Valid TUser user) {

        int i = userService.updateUserById(user);
        TUser userById = userService.getUserById(user.getId());

        return "redirect:/permission/user/list.html?search=" + userById.getLoginacct();

    }


    /**
     * 查询用户详情数据，然后跳转到edit.jsp
     * 凡是会跳转到新页面，都改成伪静态
     *
     * @return
     */
    @RequestMapping("/edit.html")
    public String toEditPage(@RequestParam(name = "id", required = true) Integer id, Model model) {

        TUser userById = userService.getUserById(id);
        model.addAttribute("user", userById);
        return "manager/permission/user_edit";
    }


    /**
     * 删除多个用户
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/del", method = {RequestMethod.POST})
    @ResponseBody
    public Msg delUsers(@RequestParam("ids") String ids) {
        logger.debug("原始ids：" + ids);

        int line = 0;
        //判断是多个员工还是单个员工
        if (ids.contains("-")) {

            String[] split = ids.split("-");
            List<Integer> idList = new ArrayList<>();
            for (String s : split) {
                String trim = s.trim();

                logger.debug("遍历的多个ids" + trim);
                idList.add(Integer.parseInt(trim));
            }

            //调用service,返回影响的行数
            line = userService.deleteBatch(idList);

        } else {
            //只有一个员工
            String trim = ids.trim();
            line = userService.deleteById(Integer.parseInt(trim));
        }
        logger.debug("影响行数" + line);

        if (line > 0) {
            //影响行数大于0认为删除成功
            return Msg.success();
        } else {
            //影响行数小于0认为删除失败
            return Msg.fail();
        }
    }


    /**
     * 查询所有用户
     *
     * @return
     */
    @RequestMapping("/list.html")
    public String findAllUser(@RequestParam(name = "page", defaultValue = "1") Integer page,
                              @RequestParam(name = "size", defaultValue = "13") Integer size,
                              @RequestParam(name = "search", defaultValue = "") String search,
                              Model model) {
        List<TUser> userList = null;
        if (search.trim() == "") {
            //没传查询条件。默认查询全部
            userList = userService.findAll(page, size);
        } else {
            //传递了查询条件，就用带查询条件的方法
            userList = userService.findAllByCondition(page, size, search);

        }
        model.addAttribute("search", search);
        PageInfo pageInfo = new PageInfo(userList);

        model.addAttribute("pageInfo", pageInfo);

        return "manager/permission/user";
    }


    /**
     * 注册用户
     *
     * @param user
     * @return
     */
    @RequestMapping("/reg")
    public String reg(@Valid TUser user, HttpSession session) {


        //生成激活码
        String uuid = UUID.randomUUID().toString();
        user.setVerifycode(uuid);
        //设置状态为未激活
        user.setStatus(0);


        //1.把用户的数据存储到数据库
        boolean flag = userService.register(user);

        //2.判断注册结果
        if (flag) {
            //注册成功，提示去邮箱激活
            String emailMsg = "众筹网账号激活，请点击以下连接激活: <a href='http://localhost:8080/manger_web_war_exploded/permission/user/active?token=" + user.getVerifycode() + "'>激活</a>";
            //发送激活邮件
            MailUtils.sendMail(user.getEmail(), "众筹网-账号激活邮件", emailMsg);
            //删除可能存在的错误提示信息
            session.removeAttribute("regError");
            session.setAttribute("info", "亲爱的用户，你已经注册成功，快到邮箱点击激活吧！");


            return "redirect:/msg.jsp";
        } else {
            //注册失败，返回注册页面
            //添加错误提示信息
            session.setAttribute("regError", "用户名重复");

            //想将用户输入的数据再次放回输入框，这里不用再将user存入域中
            //因为controller拿到的user本身就是从隐含模型中拿，现在到页面上，只需使用  ${TUser} 就能拿到.(一般用类名首字母小写，但是这个类开头两个字母都大写，因此需要换一种写法)

            //因reg.jsp不在jsps目录下，所以用forward转发
            return "redirect:/reg.jsp";
        }


    }


    /**
     * 激活用户
     *
     * @param token
     * @return
     */
    @RequestMapping("/active")
    public String active(@RequestParam("token") String token, HttpSession session) {

        if (!token.trim().equals("")) {
            //不为空，开始激活
            int i = userService.activeUser(token);

            if (i > 0) {
                session.setAttribute("info", "激活成功，快去<a href='http://localhost:8080/manger_web_war_exploded'>登录</a>吧");
                return "redirect:/msg.jsp";
            } else {
                session.setAttribute("info", "激活失败");
                return "redirect:/msg.jsp";

            }
        } else {
            session.setAttribute("info", "激活失败");
            return "redirect:/msg.jsp";

        }


    }


    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @RequestMapping("/login")
    public String login(TUser user, HttpSession session, @RequestParam(name = "remenber",required = false,defaultValue = "0") String remenber,
                        HttpServletResponse response) {

        TUser loginUser = userService.login(user);
        if (loginUser == null) {
            //登录失败
            //将错误提示展示到登录页面，用重定向就不能放request域中了，放在session中
            session.setAttribute("errorUser", user);
            session.setAttribute("msg", "登录失败，用户名或者密码错误");

            //绝对路径转发,/表示http://localhost/
            // 因为没有用到视图解析器，所以这里写全名称

            //用forward，url显示的是http://localhost:8080/manger_web_war_exploded/permission/user/login
            // return "forward:/login.jsp";
            //用redirect，显示的是：http://localhost:8080/manger_web_war_exploded/login.jsp    ，显然是这个好
            return "redirect:/login.jsp";

        } else {

            if (loginUser.getStatus() == 0) {
                //未激活
                session.setAttribute("errorUser", user);
                session.setAttribute("msg", "未激活，请到邮箱激活");

                return "redirect:/login.jsp";

            } else {
                //登录成功，把错误消息从session中移除
                session.removeAttribute("errorUser");
                session.removeAttribute("msg");

                //给session域中添加用户对象,注意这里放的是从数据库查出来的，而不是前端提交的
                session.setAttribute(MyConstants.LOGIN_USER, loginUser);


                //判断是否勾选了记住我
                if (remenber.equals("1")) {

                    //1.生成一个uuid保存到数据库
                    String tokenStr = UUID.randomUUID().toString().replace("-", "");
                    //设置参数
                    TUserToken token = new TUserToken();
                    token.setUserid(loginUser.getId());
                    token.setAutoLogin(tokenStr);

                    int i = tokenService.save(token);

                    //2.生成cookie并且返回到浏览器
                    Cookie cookie = new Cookie("autologin",tokenStr);
                    //7天 3600*24*7
                    cookie.setMaxAge(604800);
                    cookie.setPath(session.getServletContext().getContextPath());

                    response.addCookie(cookie);

                }


                //去页面调度中心，实现重定向.
                return "redirect:/main.html";
            }

        }


    }

    /**
     * 退出登录
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session, HttpServletResponse response, HttpServletRequest request){
        //1.从session中清除用户
        session.removeAttribute(MyConstants.LOGIN_USER);
        //2.删除cookie
        CookieUtils.delCookie(request,response,"autologin");


        //返回index页面
        //  manger-web/src/main/webapp/WEB-INF/jsps/index.jsp
        return "redirect:/index.jsp";

    }

    /**
     * 获取邮箱验证码
     *
     * @param loginacct
     * @return
     */
    @RequestMapping("/code")
    @ResponseBody
    public Msg getEmailCode(@RequestParam("loginacct") String loginacct, HttpSession session) {

        Long lastGetTime = (Long) session.getAttribute("lastGetTime");
        if (lastGetTime != null) {
            //不是第一次请求验证码
            //删除旧的验证码
            session.removeAttribute("emailVerifyCode");
            //获取上次请求时间
            logger.debug("上次请求时间为：" + lastGetTime);
            long nowTime = System.currentTimeMillis() / 1000;
            long intervalTime = nowTime - lastGetTime;
            if (intervalTime < 60) {
                //发送间隔小于1分钟，提示发送太快了
                return Msg.fail().add("msg", "发送的太快了，休息一会再试吧");
            }
        }
        //每次访问都更新请求时间
        session.setAttribute("lastGetTime", System.currentTimeMillis() / 1000);


        if (!loginacct.trim().equals("")) {
            //账号不能为空
            //查询这个账号对应的邮箱，发送邮件
            TUser user = userService.findUserByAccount(loginacct.trim());
            String email = user.getEmail();

            String randomCode = RandomUtils.getRandomCode();
            MailUtils.sendMail(email, "找回密码", "您的账号：" + loginacct + "正在找回密码，验证码是：" + randomCode);
            session.setAttribute("emailVerifyCode", randomCode);
            return Msg.success();
        } else {
            //账号是空的
            return Msg.fail();

        }


    }


    /**
     * 来到重置密码页面
     *
     * @param loginacct
     * @param code
     * @return
     */
    @RequestMapping("/reset.html")
    public String toResetPage(@RequestParam(value = "loginacct", required = true) String loginacct,
                              @RequestParam(value = "code", required = true) String code, HttpSession session) {

        //判断验证码是否相等
        String emailVerifyCode = (String) session.getAttribute("emailVerifyCode");

        if (code.equalsIgnoreCase(emailVerifyCode)) {
            //验证通过
            //移除旧的验证码
            session.removeAttribute("emailVerifyCode");
            //将用户放入域中，表示已经通过了邮箱验证（没通过验证？返回reset.jsp页面）
            session.setAttribute("resetUser", loginacct);

            return "manager/permission/user_password_reset";
        } else {

            //验证不通过，返回返回reset.jsp页面并且提示验证码无效
            session.setAttribute("msg", "验证码无效");

            return "redirect:/reset.jsp";
        }
    }

    /**
     * 重置用户密码
     *
     * @param password
     * @param session
     * @return
     */
    @RequestMapping("/reset")
    public String resetPassword(@RequestParam(value = "password", required = true) String password, HttpSession session, RedirectAttributesModelMap modelMap) {

        //判断前面的验证是否通过
        String resetUser = (String) session.getAttribute("resetUser");

        if (resetUser != null) {
            //通过了前面的验证
            //执行修改逻辑
            int i = userService.resetUserPassword(resetUser, password);

            if (i > 0) {
                session.setAttribute("info", "修改成功");


            } else {
                session.setAttribute("info", "修改失败");
            }

            //移除旧的验证数据,下次再访问就需要重新来
            session.removeAttribute("resetUser");
            return "redirect:/msg.jsp";
        } else {
            //没通过验证，多半是非法。那直接返回第一页，什么提示也不给

            return "redirect:/reset.jsp";

        }
    }
}
