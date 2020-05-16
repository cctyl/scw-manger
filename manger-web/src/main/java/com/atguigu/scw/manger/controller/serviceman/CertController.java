package com.atguigu.scw.manger.controller.serviceman;

import com.atguigu.scw.manger.bean.Msg;
import com.atguigu.scw.manger.bean.TCert;
import com.atguigu.scw.manger.bean.TType;
import com.atguigu.scw.manger.service.TCertService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
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





    /**
     * 删除项目分类
     *
     * @param ids
     * @return
     */
    @RequestMapping("/del")
    @ResponseBody
    public Msg delRoleById(@RequestParam("ids") String ids) {


        int line = 0;
        //判断是多个员工还是单个员工
        if (ids.contains("-")) {

            String[] split = ids.split("-");
            List<Integer> idList = new ArrayList<>();
            for (String s : split) {
                String trim = s.trim();

                idList.add(Integer.parseInt(trim));
            }

            //调用service,返回影响的行数
            line = certService.deleteBatch(idList);

        } else {
            //只有一个员工
            String trim = ids.trim();
            line = certService.deleteById(Integer.parseInt(trim));
        }


        if (line > 0) {
            //影响行数大于0认为删除成功
            return Msg.success();
        } else {
            //影响行数小于0认为删除失败
            return Msg.fail();
        }
    }



    /**
     * 添加分类
     *
     * @param cert
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public Msg addRole(TCert cert) {

        int i = certService.addRole(cert);

        if (i>0){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }

    /**
     * 修改分类
     * @param cert
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public Msg updateTypeById(TCert cert){

        int i =   certService.updateById(cert);

        if (i>0){

            return Msg.success();
        }else {

            return Msg.fail();
        }
    }










}
