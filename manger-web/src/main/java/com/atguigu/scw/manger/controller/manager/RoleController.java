package com.atguigu.scw.manger.controller.manager;

import com.atguigu.scw.manger.bean.Msg;
import com.atguigu.scw.manger.bean.TPermission;
import com.atguigu.scw.manger.bean.TRole;
import com.atguigu.scw.manger.constant.MyConstants;
import com.atguigu.scw.manger.service.TPermissionService;
import com.atguigu.scw.manger.service.TRoleService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/permission/role")
public class RoleController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    TRoleService roleService;

    @Autowired
    TPermissionService permissionService;

    /**
     * 来到角色列表-
     *
     * @param page
     * @param size
     * @param search
     * @param model
     * @return
     */
    @RequestMapping("/list.html")
    public String toRolePage(@RequestParam(name = "page", defaultValue = "1") Integer page,
                             @RequestParam(name = "size", defaultValue = "13") Integer size,
                             @RequestParam(name = "search", defaultValue = "") String search,
                             Model model) {
        logger.debug("--------- 到role.html------------");
        List<TRole> roleList = null;
        if (search.trim() == "") {
            //没传查询条件。默认查询全部
            roleList = roleService.findAll(page, size);
        } else {
            //传递了查询条件，就用带查询条件的方法
            roleList = roleService.findAllByCondition(page, size, search);

        }
        model.addAttribute("search", search);
        PageInfo pageInfo = new PageInfo(roleList);

        model.addAttribute("pageInfo", pageInfo);


        return "manager/permission/role";
    }


    /**
     * 来到权限分配页面
     *
     * @return
     */
    @RequestMapping("/assignPermission.html")
    public String toAssignPermissionPage(@RequestParam("rid") Integer rid, Model model) {
        //查出所有权限
        List<TPermission> permissions = permissionService.getAllPermission();
        //查出角色已经拥有的权限id
        List<Integer> rolePermissionIdByRoleId = permissionService.getRolePermissionIdByRoleId(rid);

        for (TPermission permission : permissions) {
            if (rolePermissionIdByRoleId.contains(permission.getId())) {
                permission.setFlag("checked='checked'");
            }
        }

        for (TPermission permission : permissions) {
           logger.debug(permission.getFlag()+"");
        }

        List<TPermission> sortPermission = sortPermission(permissions, 0);
        model.addAttribute("permissionList", sortPermission);


        return "manager/permission/assignPermission";
    }

    //整理
    public List<TPermission> sortPermission(List<TPermission> list, Integer pid) {
        List<TPermission> child = new ArrayList<>();

        for (TPermission current : list) {
            Integer myPid = current.getPid();
            if (myPid == pid) {
                //说明是pid的子节点
                child.add(current);
                List<TPermission> permissions = sortPermission(list, current.getId());//自己作为父级，传自己的5
                current.setChilds(permissions);
            }

        }
        return child;
    }

}
