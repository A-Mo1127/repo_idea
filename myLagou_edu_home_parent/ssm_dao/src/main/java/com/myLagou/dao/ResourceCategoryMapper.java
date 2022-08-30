package com.myLagou.dao;

import com.myLagou.entity.ResourceCategory;

import java.util.List;

/**
 * @author zhy
 * @create 2022-08-28 10:49
 */
public interface ResourceCategoryMapper {

    /*查找所有的资源分类*/
    public List<ResourceCategory> findAllResourceCategory();

}
