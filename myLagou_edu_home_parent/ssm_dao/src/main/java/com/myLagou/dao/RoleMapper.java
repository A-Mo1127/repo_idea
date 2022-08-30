package com.myLagou.dao;

import com.myLagou.entity.Role;
import com.myLagou.entity.Role_menu_relation;

import java.util.List;

/**
 * @author zhy
 * @create 2022-08-26 13:14
 */
public interface RoleMapper {

    /*查询所有角色&根据条件进行查询*/
    public List<Role> findAllRole(Role role);//根据条件查询时有参数

    /*根据角色id查询改角色关联的菜单信息*/
    public List<Integer> findMenuByRoleId(Integer roleId);


    /*根据roleId清空角色和菜单的中间表关联关系*/
    public void deleteRoleContextMenu(Integer roleId);


    /*为角色分配菜单信息*/
    public void saveRoleContextMenu(Role_menu_relation role_menu_relation);

    /*添加角色*/
    public void saveRole(Role role);


    /*修改角色*/
    public void updateRole(Role role);


    /*删除角色*/
    public void deleteRole(Integer roleId);


}
