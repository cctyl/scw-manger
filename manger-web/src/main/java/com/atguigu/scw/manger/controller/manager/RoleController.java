package com.atguigu.scw.manger.controller.manager;

import com.atguigu.scw.manger.bean.Msg;
import com.atguigu.scw.manger.bean.TPermission;
import com.atguigu.scw.manger.bean.TRole;
import com.atguigu.scw.manger.bean.TRolePermission;
import com.atguigu.scw.manger.constant.MyConstants;
import com.atguigu.scw.manger.dao.TRolePermissionMapper;
import com.atguigu.scw.manger.service.TPermissionService;
import com.atguigu.scw.manger.service.TRolePermissionService;
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

    @Autowired
    TRolePermissionService rolePermissionService;

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
        List<TPermission> permissionList = permissionService.getAllPermission();
        //查出角色已经拥有的权限id
        List<Integer> idList = permissionService.getRolePermissionIdByRoleId(rid);


        for (TPermission tPermission : permissionList) {

            if (idList.contains(tPermission.getId())) {
                logger.debug("contains:--" + tPermission.getId());
                tPermission.setChk("checked='checked'");
            } else {
                tPermission.setChk("No!!");
            }
        }

        List<TPermission> sortPermission = sortPermission(permissionList, 0);

        model.addAttribute("sort", sortPermission);

        return "manager/permission/assignPermission";
    }

    @RequestMapping("/assginPer")
    @ResponseBody
    public Msg assginPermissionByRid(TRolePermission rolePermission,
                                     @RequestParam("opt") String opt) {

        logger.debug("收到的rid是：" + rolePermission.getRoleid() + "收到的pid是：" + rolePermission.getPermissionid()+"方法是："+opt);
        int result =0;
        switch (opt){
            case "add":
                result=rolePermissionService.addPermission(rolePermission);
                break;

            case "del":
                result=rolePermissionService.delPermission(rolePermission);
                break;
        }

        if (result>0){
            return Msg.success();
        }else {
            return Msg.fail();
        }


    }







    /**
     * 整理子父级关系
     *
     * @param list
     * @param pid
     * @return
     */
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
