package com.myLagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myLagou.dao.UserMapper;
import com.myLagou.entity.*;
import com.myLagou.service.UserService;
import com.myLagou.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author zhy
 * @create 2022-08-25 21:54
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /*用户分页多条件组合查询*/
    @Override
    public PageInfo findAllUserByPage(UserVo userVo) {

        PageHelper.startPage(userVo.getCurrentPage(), userVo.getPageSize());
        List<User> userList = userMapper.findAllUserByPage(userVo);

        PageInfo<User> userPageInfo = new PageInfo<>(userList);
        return userPageInfo;
    }

    @Override
    public void updateUserStatus(int id, String status) {
        User user = new User();
        user.setId(id);
        user.setUpdate_time(new Date());
        user.setStatus(status);
        userMapper.updateUserStatus(user);
    }

    @Override
    public User login(User user) throws Exception {
        //调用dao层方法，获取user对象，此user对象中包含了用户的具体信息
        User loginUser = userMapper.login(user);

        //在获取的user对象不为空的前提下，对获取的user对象和前端传递过来的user对象的密文密码进行对比校验
        if (loginUser != null && Md5.verify(user.getPassword(), "lagou", loginUser.getPassword())){
            //校验通过直接返回获取的user对象
            return loginUser;
        }else {
            //获取的user对象为空，直接返回null
            System.out.println("密码错误！！！");
            return null;

        }
    }

    /*分配角色的回显功能*/
    @Override
    public List<Role> findUserRelationRoleById(Integer id) {
        List<Role> userRelationRoleById = userMapper.findUserRelationRoleById(id);

        return userRelationRoleById;
    }

    /*给用户分配角色*/
    @Override
    public void userContextRole(UserVo userVo) {
        //先清空中间表
        userMapper.deleteUserContextRole(userVo.getUserId());

        List<Integer> roleIdList = userVo.getRoleIdList();
        for (Integer roleId : roleIdList) {
            //封装数据
            User_Role_relation user_role_relation = new User_Role_relation();

            user_role_relation.setUserId(userVo.getUserId());
            user_role_relation.setRoleId(roleId);

            Date date = new Date();
            user_role_relation.setCreatedTime(date);
            user_role_relation.setUpdatedTime(date);

            user_role_relation.setCreatedBy("system");
            user_role_relation.setUpdatedby("system");

            //再插入新的用户角色关系
            userMapper.userContextRole(user_role_relation);
        }

    }


    /*动态获取用户权限*/
    @Override
    public ResponseResult getUserPermission(Integer userId) {
        //1、根据当前用户的id查询该用户所具有的角色
        List<Role> roleList = userMapper.findUserRelationRoleById(userId);
        //2、获取角色的id，保存到List集合中
        List<Integer> roleIdlist = new ArrayList<>();
        for (Role role : roleList) {
            Integer roleId = role.getId();
            roleIdlist.add(roleId);
        }

        //3、根据角色id查询父级菜单
        List<Menu> parentMenuByRoleId = userMapper.findParentMenuByRoleId(roleIdlist);

        //4、再对父级菜单关联的子级菜单进行关联查询
        for (Menu menu : parentMenuByRoleId) {
            int parentId = menu.getParentId();
            List<Menu> subMenuByPid = userMapper.findSubMenuByPid(parentId);
            //把父级菜单的子菜单进行封装到menu中
            menu.setSubMenuList(subMenuByPid);
        }

        //5、获取资源信息
        List<Resource> resourceList = userMapper.findUserResourceByRoleId(roleIdlist);


        //6、封装数据并进行返回
        Map<String, Object> map = new HashMap<>();
        map.put("menuList",parentMenuByRoleId);
        map.put("resourceList",resourceList);

        return new ResponseResult(true,200, "动态获取权限信息成功", map);
    }
}
