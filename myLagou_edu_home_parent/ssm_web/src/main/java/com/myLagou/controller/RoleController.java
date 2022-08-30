package com.myLagou.controller;

import com.myLagou.entity.Menu;
import com.myLagou.entity.ResponseResult;
import com.myLagou.entity.Role;
import com.myLagou.entity.RoleMenuVo;
import com.myLagou.service.MenuService;
import com.myLagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhy
 * @create 2022-08-26 13:36
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

    /*查询所有角色&根据条件进行查询*/
    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){

        List<Role> allRole = roleService.findAllRole(role);

        ResponseResult result = new ResponseResult(true, 200, "查询所有角色&根据条件进行查询成功", allRole);
        return result;
    }


    /*查询所有父子菜单信息*/
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){
        //parent_id为-1的为顶级菜单（父级菜单
        List<Menu> allMenu = menuService.findAllMenu(-1);

        //这样直接返回与接口文档所要求的格式不同，所以需要重新写
        //ResponseResult result = new ResponseResult(true, 200, "查询所有父子菜单信息成功", allMenu);

        //按照接口文档的响应格式要求
        Map<String, Object> map = new HashMap<>();
        map.put("parentMenuList", allMenu);
        ResponseResult result = new ResponseResult(true, 200, "查询所有父子菜单信息成功", map);

        return result;
    }

    /*根据角色id查询该角色关联的菜单信息*/
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleId){
        List<Integer> menuByRoleId = roleService.findMenuByRoleId(roleId);
        ResponseResult result = new ResponseResult(true, 200, "查询角色关联的菜单信息成功", menuByRoleId);
        return result;
    }


    /*为角色分配菜单*/
    @RequestMapping("/RoleContextMenu")
    public ResponseResult RoleContextMenu(@RequestBody RoleMenuVo roleMenuVo){
        roleService.roleContextMenu(roleMenuVo);

        ResponseResult result = new ResponseResult(true, 200, "为角色分配菜单成功", null);
        return result;

    }

    /*新增角色*/
    @RequestMapping("/saveOrUpdateRole")
    public ResponseResult saveOrUpdateRole(@RequestBody Role role){
        if (role.getId() == null){
            roleService.saveRole(role);
            return new ResponseResult(true,200, "新增角色成功", null);
        }else {
            roleService.updateRole(role);
            return new ResponseResult(true,200, "修改角色成功", null);
        }

    }


    /*删除角色*/
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer id) {
        roleService.deleteRole(id);

        ResponseResult result = new ResponseResult(true, 200, "删除角色成功", null);
        return result;
    }

}
