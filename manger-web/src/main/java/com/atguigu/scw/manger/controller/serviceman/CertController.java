package com.atguigu.scw.manger.controller.serviceman;

import com.atguigu.scw.manger.bean.TCert;
import com.atguigu.scw.manger.service.TCertService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("service/cert")
public class CertController {


    @Autowired
    TCertService certService;


    /**
     * 查询所有的资质，展示到页面
     * 依然尝试分页查询
     *
     * @param page
     * @param size
     * @param search
     * @param model
     * @return
     */
    @RequestMapping("/list.html")
    public String certList(@RequestParam(name = "page", defaultValue = "1") Integer page,
                           @RequestParam(name = "size", defaultValue = "10") Integer size,
                           @RequestParam(name = "search", defaultValue = "") String search,
                           Model model) {


        List<TCert> certList = certService.findAllByCondition(page, size, search);

        PageInfo pageInfo = new PageInfo(certList);
        //封装pageBean
        model.addAttribute("pageInfo",pageInfo);
        //将关键字回显
        model.addAttribute("search",search);

        return "manager/service/cert";

    }
}
