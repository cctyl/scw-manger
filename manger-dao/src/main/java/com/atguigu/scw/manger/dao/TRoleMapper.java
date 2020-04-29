package com.atguigu.scw.manger.dao;

import com.atguigu.scw.manger.bean.TRole;
import com.atguigu.scw.manger.example.TRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import javax.management.relation.Role;

public interface TRoleMapper {
    long countByExample(TRoleExample example);

    int deleteByExample(TRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TRole record);

    int insertSelective(TRole record);

    List<TRole> selectByExample(TRoleExample example);

    TRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TRole record, @Param("example") TRoleExample example);

    int updateByExample(@Param("record") TRole record, @Param("example") TRoleExample example);

    int updateByPrimaryKeySelective(TRole record);

    int updateByPrimaryKey(TRole record);

    /**
     * 根据用户id查询用户拥有的角色
     * @param id
     * @return
     */
    @Select("SELECT * FROM t_role WHERE id  IN(SELECT roleId FROM t_user_role  WHERE userId =#{id} )")
    public List<TRole> findRoleByUid(Integer id);

    /**
     * 查询用户没有的角色
     * @param id
     * @return
     */
    @Select("SELECT * FROM t_role WHERE  id NOT IN(SELECT roleId FROM t_user_role  WHERE userId =#{id} )")
    public List<TRole> findOthersRole(Integer id);
}