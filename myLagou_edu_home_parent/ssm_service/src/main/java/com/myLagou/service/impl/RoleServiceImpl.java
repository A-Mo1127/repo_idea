package com.myLagou.service.impl;

import com.myLagou.dao.RoleMapper;
import com.myLagou.entity.Role;
import com.myLagou.entity.RoleMenuVo;
import com.myLagou.entity.Role_menu_relation;
import com.myLagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author zhy
 * @create 2022-08-26 13:32
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAllRole(Role role) {
        List<Role> allRole = roleMapper.findAllRole(role);

        return allRole;
    }

    @Override
    public List<Integer> findMenuByRoleId(Integer roleId) {
        List<Integer> list = roleMapper.findMenuByRoleId(roleId);

        return list;
    }

    /*为角色分配菜单*/
    @Override
    public void roleContextMenu(RoleMenuVo roleMenuVo) {
        //1、根据roleId清空角色和菜单的中间表关联关系
        roleMapper.deleteRoleContextMenu(roleMenuVo.getRoleId());

        /*2、为角色分配菜单信息*/
        for (Integer mid : roleMenuVo.getMenuIdList()) {
            Role_menu_relation role_menu_relation = new Role_menu_relation();

            role_menu_relation.setMenuId(mid);
            role_menu_relation.setRoleId(roleMenuVo.getRoleId());

            Date date = new Date();
            role_menu_relation.setCreatedTime(date);
            role_menu_relation.setUpdatedTime(date);

            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedby("system");

            roleMapper.saveRoleContextMenu(role_menu_relation);

        }
    }

    @Override
    public void deleteRole(Integer roleId) {
        //根据roleId清空角色和菜单的中间表的关联关系
        roleMapper.deleteRoleContextMenu(roleId);

        roleMapper.deleteRole(roleId);
    }

    @Override
    public void saveRole(Role role) {
        //封装数据
        role.setCreatedBy("system");
        role.setUpdatedBy("system");
        Date date = new Date();
        role.setCreatedTime(date);
        role.setUpdatedTime(date);

        roleMapper.saveRole(role);
    }

    @Override
    public void updateRole(Role role) {
        role.setUpdatedBy("system");
        role.setUpdatedTime(new Date());

        roleMapper.updateRole(role);
    }
}
