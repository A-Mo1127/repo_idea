package com.myLagou.service;

import com.myLagou.dao.RoleMapper;
import com.myLagou.entity.Role;
import com.myLagou.entity.RoleMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhy
 * @create 2022-08-26 13:31
 */

public interface RoleService {

    /*查询所有角色&根据条件进行查询*/
    public List<Role> findAllRole(Role role);//根据条件查询时有参数


    /*根据角色id查询改角色关联的菜单信息*/
    public List<Integer> findMenuByRoleId(Integer roleId);

    /*为角色分配菜单*/
    public void roleContextMenu(RoleMenuVo roleMenuVo);

    /*删除角色*/
    public void deleteRole(Integer roleId);

    /*添加角色*/
    public void saveRole(Role role);


    /*修改角色*/
    public void updateRole(Role role);


}
