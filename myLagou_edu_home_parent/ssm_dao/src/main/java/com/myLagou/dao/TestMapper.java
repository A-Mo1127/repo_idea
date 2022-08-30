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
    
    //做出修改
     public List<Test> findAllTest2();

     //测试产生冲突
    public void test1();
    public void test2();
    public void test3();
    public void test4();
    public void test5();
    public void test8();
    public void test6();
    public void test7();

}
