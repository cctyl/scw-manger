package com.atguigu.scw.manger.controller.manager;

import com.atguigu.scw.manger.bean.TRole;
import com.atguigu.scw.manger.service.TRoleService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/permission/role")
public class RoleController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    TRoleService roleService;

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


}
