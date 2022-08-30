package com.myLagou.controller;

import com.myLagou.entity.Menu;
import com.myLagou.entity.ResponseResult;
import com.myLagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.spi.ResolveResult;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author zhy
 * @create 2022-08-26 14:43
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    /*查询所有菜单*/
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu() {
        List<Menu> menus = menuService.findMenus();

        ResponseResult result = new ResponseResult(true, 200, "查找所有菜单成功", menus);
        return result;
    }


    /*回显菜单信息*/
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(Integer id) {
        //根据id的值判断当前操作是新增菜单还是更新菜单的回显操作，如果id值为-1，则表示是新增
        if (id == -1) {
            //新增菜单操作的回显，  回显信息中不需要查询menu信息，只需要显示父级的菜单信息
            List<Menu> menuSubList = menuService.findAllMenu(-1);

            //根据响应数据格式来封装数据
            Map<String, Object> map = new HashMap<>();
            map.put("menuInfo", null);
            map.put("parentMenuList", menuSubList);

            ResponseResult result = new ResponseResult(true, 200, "添加操作的回显信息成功", map);
            return result;
        } else {
            //修改操作的回显信息，需要回显所有的菜单信息，包括父级以及当前id所对应的menu信息
            Menu menuInfo = menuService.findMenuById(id);
            List<Menu> parentMenuList = menuService.findAllMenu(-1);

            Map<String, Object> map = new HashMap<>();
            map.put("menuInfo", menuInfo);
            map.put("parentMenuList", parentMenuList);

            return new ResponseResult(true, 200, "更新操作菜单回显成功", map);
        }

    }

    /*新增&修改权限*/
    @RequestMapping("/saveOrUpdateMenu")
    public ResponseResult saveOrUpdateMenu(@RequestBody Menu menu){
        if (menu.getId() == null){//新增
            menuService.saveMenu(menu);

            return new ResponseResult(true,200,"新增权限成功",null);

        }else {//修改
            menuService.updateMenu(menu);

            return new ResponseResult(true,200,"修改权限成功",null);

        }

    }



}
