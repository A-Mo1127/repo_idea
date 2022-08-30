package com.myLagou.service;

import com.github.pagehelper.PageInfo;
import com.myLagou.entity.ResponseResult;
import com.myLagou.entity.Role;
import com.myLagou.entity.User;
import com.myLagou.entity.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhy
 * @create 2022-08-25 21:54
 */
public interface UserService {
    /*用户分页多条件组合查询*/
    public PageInfo findAllUserByPage(UserVo userVo);

    /*修改用户状态*/
    public void updateUserStatus(int id, String status);


    /*用户登录     根据用户名查询具体的用户信息    */
    public User login(User user) throws Exception;

    /*分配角色的回显功能*/
    public List<Role> findUserRelationRoleById(Integer id);


    /*给用户分配角色*/
    public void userContextRole(UserVo userVo);

    /*动态获取用户权限*/
    public ResponseResult getUserPermission(Integer userId);//找个参数id是从session中获取的

}
