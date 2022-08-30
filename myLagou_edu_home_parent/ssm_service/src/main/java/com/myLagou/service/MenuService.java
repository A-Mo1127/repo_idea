package com.myLagou.service;

import com.myLagou.entity.Menu;

import java.util.List;

/**
 * @author zhy
 * @create 2022-08-26 14:42
 */
public interface MenuService {

    /*查询所有父子菜单信息*/
    public List<Menu> findAllMenu(int pid);//在嵌套查询子级菜单时需要父级的id

    /*查询所以菜单信息*/
    public List<Menu> findMenus();

    /*根据id查询menu信息*/
    public Menu findMenuById(Integer id);


    /*新增权限(菜单)*/
    public void saveMenu(Menu menu);

    /*修改权限(菜单)*/
    public void updateMenu(Menu menu);
}
