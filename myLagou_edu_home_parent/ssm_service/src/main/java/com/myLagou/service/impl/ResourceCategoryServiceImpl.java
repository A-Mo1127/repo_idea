package com.myLagou.service.impl;

import com.myLagou.dao.ResourceCategoryMapper;
import com.myLagou.entity.ResourceCategory;
import com.myLagou.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhy
 * @create 2022-08-28 10:53
 */
@Service
public class ResourceCategoryServiceImpl implements ResourceCategoryService {
    @Autowired
    private ResourceCategoryMapper resourceCategoryMapper;

    @Override
    public List<ResourceCategory> findAllResourceCategory() {
        List<ResourceCategory> categoryList = resourceCategoryMapper.findAllResourceCategory();

        return categoryList;
    }
}
