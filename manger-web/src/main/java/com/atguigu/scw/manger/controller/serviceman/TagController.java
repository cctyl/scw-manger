package com.atguigu.scw.manger.controller.serviceman;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/service/tag")
public class TagController {

    /**
     * 来到project_tag.jsp页面
     * @return
     */
    @RequestMapping("/tag.html")
    public String toTagPage(){

        return "manager/service/project_tag";
    }
}
