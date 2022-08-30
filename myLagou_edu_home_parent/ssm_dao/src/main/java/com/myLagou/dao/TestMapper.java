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
    public void test11();
    public void test22();
    public void test33();
    public void test44();
    public void test55();
    public void test86();
    public void test67();
    public void test78();
    

}
