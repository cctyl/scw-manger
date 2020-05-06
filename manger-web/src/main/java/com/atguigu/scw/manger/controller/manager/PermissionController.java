package com.atguigu.scw.manger.controller.manager;

import com.atguigu.scw.manger.bean.TPermission;
import com.atguigu.scw.manger.service.TPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/permission/perm")
public class PermissionController {


    @Autowired
    TPermissionService permissionService;

    /**
     * 来到权限展示页面
     * @return
     */
    @RequestMapping("/permission.html")
    public String toPermissionPage(Model model){
        List<TPermission> allMenus = permissionService.getAllMenus();
        model.addAttribute("sort",allMenus);

        return "manager/permission/permission";
    }

}
