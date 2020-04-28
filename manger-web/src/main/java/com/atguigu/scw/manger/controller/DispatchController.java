package com.atguigu.scw.manger.controller;

import com.atguigu.scw.manger.bean.TPermission;
import com.atguigu.scw.manger.constant.MyConstants;
import com.atguigu.scw.manger.service.TPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 页面调度中心
 * 负责重定向到指定页面，并且查询指定页面的数据
 */
@Controller
public class DispatchController {

    @Autowired
    TPermissionService permissionService;


    @RequestMapping("/main.html")
    public String toMainPage(HttpSession session) {


        if (session.getAttribute(MyConstants.USER_MENUS) == null) {
            //查询出页面的数据放进去
            List<TPermission> menus = permissionService.getAllMenus();
            session.setAttribute(MyConstants.USER_MENUS, menus);
        }
        return "manager/main";
    }


}


