package com.myLagou.controller;

import com.myLagou.entity.ResourceCategory;
import com.myLagou.entity.ResponseResult;
import com.myLagou.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhy
 * @create 2022-08-28 10:55
 */
@RestController
@RequestMapping("/ResourceCategory")
public class ResourceCategoryController {
    @Autowired
    private ResourceCategoryService resourceCategoryService;

    /*查找所有的资源分类*/
    @RequestMapping("/findAllResourceCategory")
    public ResponseResult findAllResourceCategory(){
        List<ResourceCategory> categoryList = resourceCategoryService.findAllResourceCategory();

        ResponseResult result = new ResponseResult(true, 200, "查找所有资源分类成功", categoryList);
        return result;
    }



}
