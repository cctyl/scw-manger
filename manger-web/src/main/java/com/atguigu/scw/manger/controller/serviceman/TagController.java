package com.atguigu.scw.manger.controller.serviceman;

import com.atguigu.scw.manger.bean.Msg;
import com.atguigu.scw.manger.bean.TTag;
import com.atguigu.scw.manger.service.TTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/service/tag")
public class TagController {

    @Autowired
    TTagService tagService;

    /**
     * 来到project_tag.jsp页面
     * @return
     */
    @RequestMapping("/tag.html")
    public String toTagPage(){

        return "manager/service/project_tag";
    }


    @RequestMapping("/del")
    @ResponseBody
    public Msg delTagById(@RequestParam("id") Integer id){
      int  i =   tagService.delTagById(id);
      if (i>0){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }


    /**
     * 查询所有标签信息
     * @return
     */
    @RequestMapping("/getTag")
    @ResponseBody
    public List<TTag> getTagListByJson(){

        List<TTag> tagList = tagService.getTagList();

        return tagList;
    }

    /**
     * 修改标签名称
     * @param tag
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public Msg updateTagById(TTag tag){

       int i  = tagService.updateTagById(tag);
       if (i>0){
           return Msg.success();
       }else {
           return Msg.fail();
       }


    }












}
