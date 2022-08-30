package com.myLagou.service.impl;

import com.myLagou.dao.MenuMapper;
import com.myLagou.entity.Menu;
import com.myLagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author zhy
 * @create 2022-08-26 14:42
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> findAllMenu(int pid) {
        List<Menu> allMenu = menuMapper.findAllMenu(pid);

        return allMenu;
    }

    @Override
    public List<Menu> findMenus() {
        List<Menu> menus = menuMapper.findMenus();

        return menus;
    }

    @Override
    public Menu findMenuById(Integer id) {
        return menuMapper.findMenuById(id);

    }

    @Override
    public void saveMenu(Menu menu) {
        //封装数据
        if (menu.getParentId() == -1){
            menu.setLevel(0);
        }else {
            menu.setLevel(1);
        }

        Date date = new Date();
        menu.setCreatedTime(date);
        menu.setUpdatedTime(date);

        menu.setCreatedBy("system");
        menu.setUpdatedBy("system");

        menuMapper.saveMenu(menu);

    }

    @Override
    public void updateMenu(Menu menu) {
        menu.setUpdatedTime(new Date());
        menu.setUpdatedBy("system22222");

        menuMapper.updateMenu(menu);
    }
}
