package com.atguigu.scw.manger.controller.manager;

import com.atguigu.scw.manger.bean.Msg;
import com.atguigu.scw.manger.bean.TPermission;
import com.atguigu.scw.manger.service.TPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/permission/perm")
public class PermissionController {


    @Autowired
    TPermissionService permissionService;

    /**
     * 来到权限展示页面
     *
     * @return
     */
    @RequestMapping("/permission.html")
    public String toPermissionPage(Model model) {
        List<TPermission> allMenus = permissionService.getAllMenus();
        model.addAttribute("sort", allMenus);

        return "manager/permission/permission";
    }


    /**
     * 删除权限以及它下面的子权限
     *
     * @param id
     * @return
     */
    @RequestMapping("/del")
    @ResponseBody
    public Msg delPermissionById(@RequestParam("id") Integer id) {

        int i = permissionService.delPermissionById(id);

        if (i > 0) {
            return Msg.success();
        } else {
            return Msg.fail();
        }


    }
}
