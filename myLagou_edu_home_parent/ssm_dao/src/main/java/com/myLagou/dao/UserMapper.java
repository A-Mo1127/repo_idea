package com.myLagou.dao;

import com.myLagou.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhy
 * @create 2022-08-25 21:34
 */
public interface UserMapper {
    /*用户分页多条件组合查询*/
    public List<User> findAllUserByPage(UserVo userVo);

    /*修改用户状态*/
    public void updateUserStatus(User user);

    /*用户登录     根据用户名查询具体的用户信息     */
    public User login(User user);

    /*根据用户id清空用户角色的中间表*/
    public void deleteUserContextRole(Integer userId);

    /*分配角色*/
    public void userContextRole(User_Role_relation user_role_relation);

    /*根据用户id查询相关的角色信息*/
    public List<Role> findUserRelationRoleById(Integer id);

    /*2、根据角色id去查询角色所拥有的顶级菜单（父级菜单*/
    public List<Menu> findParentMenuByRoleId(List<Integer> ids);

    /*3、根据父级菜单的PID，查询子级菜单信息*/
    public List<Menu> findSubMenuByPid(Integer pid);

    /*4、获取用户拥有的资源权限信息*/
    public List<Resource> findUserResourceByRoleId(List<Integer> ids);

}
