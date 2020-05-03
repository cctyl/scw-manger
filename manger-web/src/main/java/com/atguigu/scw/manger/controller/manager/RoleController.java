package com.atguigu.scw.manger.controller.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/permission/role")
public class RoleController {
    Logger logger = LoggerFactory.getLogger(getClass());


    @RequestMapping("/role.html")
    public String toRolePage(){

    logger.debug("--------- 到role.html------------");
        return "manager/permission/role";
    }




}
