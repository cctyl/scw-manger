package com.atguigu.scw.manger.service.imp;

import com.atguigu.scw.manger.bean.TPermission;
import com.atguigu.scw.manger.dao.TPermissionMapper;
import com.atguigu.scw.manger.service.TPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TPermissionServiceImpl implements TPermissionService {

    @Autowired
    TPermissionMapper permissionMapper;

    /**
     * 获取所有的菜单，并且整理好父子级别关系
     * list是有序的，ArrayList适合查找多增删少
     *
     * @return
     */
    public List<TPermission> getAllMenus() {

        //example为空就默认查询全部
        List<TPermission> list = permissionMapper.selectByExample(null);


        /*整理父子节点关系
            思路：将这个父节点下的所有子节点的TPermission ，存到父节点里面
            也就是 父TPermission中有 一堆子节点List<TPermission>
            二叉树遍历算法
         */


        //将子节点放到父TPermission中
        //1.查出所有的父TPermission  根据pid=0
        //2.遍历父TPermission
        //2.1 根据当前父TPermission的id，查询出所有的子节点，并设置到父TPermission中

        List<TPermission> permissions = sortMeuns(list, 0);


        return permissions;

    }

    //整理
    public List<TPermission> sortMeuns(List<TPermission> list, Integer pid) {
        List<TPermission> child = new ArrayList<>();

        for (TPermission current : list) {
            Integer myPid = current.getPid();
            if (myPid == pid) {
                //说明是pid的子节点
                child.add(current);
                List<TPermission> permissions = sortMeuns(list, current.getId());//自己作为父级，传自己的5
                current.setChilds(permissions);
            }

        }
        return child;
    }


}
