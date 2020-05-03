package com.atguigu.scw.manger.controller.manager;


import com.atguigu.scw.manger.bean.Msg;
import com.atguigu.scw.manger.bean.TRole;
import com.atguigu.scw.manger.bean.TUser;
import com.atguigu.scw.manger.constant.MyConstants;
import com.atguigu.scw.manger.service.TRoleService;
import com.atguigu.scw.manger.service.TUserRoleService;
import com.atguigu.scw.manger.service.UserService;
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

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * 分配角色
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

        return "manager/permission/add";
    }


    /**
     * 用户添加
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
        return "manager/permission/edit";
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


        //1.把用户的数据存储到数据库
        boolean flag = userService.register(user);

        //2.判断注册结果
        if (flag) {
            //注册成功，返回控制面板
            //将已经登录的用户放到session中, key使用的常量
            session.setAttribute(MyConstants.LOGIN_USER, user);
            //为了防止页面重复提交，那么我们就对他进行重定向

            //删除可能存在的错误提示信息
            session.removeAttribute("regError");
            return "redirect:/main.html";
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
     * 用户登录
     *
     * @param user
     * @return
     */
    @RequestMapping("/login")
    public String login(TUser user, HttpSession session) {

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

            //登录成功，把错误消息从session中移除
            session.removeAttribute("errorUser");
            session.removeAttribute("msg");

            //给session域中添加用户对象,注意这里放的是从数据库查出来的，而不是前端提交的
            session.setAttribute(MyConstants.LOGIN_USER, loginUser);

            //去页面调度中心，实现重定向
            return "redirect:/main.html";
        }


    }
}
