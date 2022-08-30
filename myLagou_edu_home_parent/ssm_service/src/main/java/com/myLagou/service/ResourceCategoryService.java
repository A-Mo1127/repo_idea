package com.myLagou.service;

import com.myLagou.entity.ResourceCategory;

import java.util.List;

/**
 * @author zhy
 * @create 2022-08-28 10:53
 */
public interface ResourceCategoryService {

    /*查找所有的资源分类*/
    public List<ResourceCategory> findAllResourceCategory();

}
