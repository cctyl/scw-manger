package com.atguigu.scw.manger.controller.permission;

import com.atguigu.scw.manger.bean.Msg;
import com.atguigu.scw.manger.bean.TPermission;
import com.atguigu.scw.manger.constant.MyConstants;
import com.atguigu.scw.manger.service.TPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/permission/perm")
public class PermissionController {


    @Autowired
    TPermissionService permissionService;


    /**
     * 修改权限信息
     * @return
     */
    @RequestMapping("/update")
    public String editPermissionById(TPermission permission, HttpSession session){

        permissionService.updatePermission(permission);

        //更新菜单的显示
        return "redirect:/permission/perm/permission.html";
    }


    /**
     * 来到角色修改页面
     * @param id
     * @return
     */
    @RequestMapping("/edit.html")
    public String toEditPermissionPage(@RequestParam("id") Integer id,Model model){

        TPermission permById = permissionService.findPermById(id);

        model.addAttribute("permission",permById);
        return "manager/permission/permission_edit";

    }


    /**
     * 添加权限
     * @param permission
     * @return
     */
    @RequestMapping("/add")
    public String addPermission(TPermission permission){

        permissionService.addPermission(permission);


        return "redirect:/permission/perm/permission.html";
    }

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


    /**
     * 来到权限添加页面
     * @param pid
     * @return
     */
    @RequestMapping("/add.html")
    public String toAddPermissionPage(@RequestParam("pid") Integer pid,Model model){

        if (pid!=0){

            TPermission permission = permissionService.findPermById(pid);

            model.addAttribute("permission",permission);
        }else {
            //说明添加的是第二级权限，父权限是顶级权限，在数据库中没有设置，那就不查了，直接返回吧
            TPermission permission = new TPermission();
            permission.setName("系统权限菜单");
            permission.setId(0);
            model.addAttribute("permission",permission);
        }
        return "manager/permission/permission_add";

    }
}
