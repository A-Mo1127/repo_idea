package com.myLagou.dao;

import com.myLagou.entity.Test;

import java.util.List;

/**
 * @author zhy
 * @create 2022-08-23 15:53
 */

public interface TestMapper {

    /*对test表进行查询所有*/
    public List<Test> findAllTest();

}
