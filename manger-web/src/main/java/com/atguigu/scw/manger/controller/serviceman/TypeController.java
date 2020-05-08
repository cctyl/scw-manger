package com.atguigu.scw.manger.controller.serviceman;

import com.atguigu.scw.manger.bean.Msg;
import com.atguigu.scw.manger.bean.TRole;
import com.atguigu.scw.manger.bean.TTag;
import com.atguigu.scw.manger.bean.TType;
import com.atguigu.scw.manger.service.TTypeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/service/type")
public class TypeController {

    @Autowired
    TTypeService typeService;

    /**
     * 来到项目分类页面
     *
     * @return
     */
    @RequestMapping("/type.html")
    public String toTypePage(@RequestParam(name = "page", defaultValue = "1") Integer page,
                             @RequestParam(name = "size", defaultValue = "13") Integer size,
                             @RequestParam(name = "search", defaultValue = "") String search,
                             Model model) {

        List<TType> typeList = null;

        typeList = typeService.findAllByCondition(page, size, search);


        model.addAttribute("search", search);
        PageInfo pageInfo = new PageInfo(typeList);

        model.addAttribute("pageInfo", pageInfo);


        return "manager/service/project_type";
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
            line = typeService.deleteBatch(idList);

        } else {
            //只有一个员工
            String trim = ids.trim();
            line = typeService.deleteById(Integer.parseInt(trim));
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
     * @param type
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public Msg addRole(TType type) {

        int i = typeService.addRole(type);

        if (i>0){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }


}
