package com.myLagou.controller;

import com.github.pagehelper.PageInfo;
import com.myLagou.entity.Resource;
import com.myLagou.entity.ResourceVo;
import com.myLagou.entity.ResponseResult;
import com.myLagou.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhy
 * @create 2022-08-28 10:00
 */
@RestController
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    /*资源分页和多条件查询*/
    @RequestMapping("/findAllResource")
    public ResponseResult findAllResource(@RequestBody ResourceVo resourceVo){

        PageInfo<Resource> pageInfo = resourceService.findAllResourceByPage(resourceVo);

        ResponseResult result = new ResponseResult(true, 200, "分页查找资源成功", pageInfo);
        return result;
    }


    /*新增&更新资源*/
    @RequestMapping("/saveOrUpdateResource")
    public ResponseResult saveOrUpdateResource(@RequestBody Resource resource){

        if (resource.getId() == null){//新增
            resourceService.saveResource(resource);

            return new ResponseResult(true,200, "新增资源成功",null);
        }else {//修改
            resourceService.saveResource(resource);

            return new ResponseResult(true,200, "修改资源成功",null);
        }
    }


    /*根据id删除资源*/
    @RequestMapping("/deleteResource")
    public ResponseResult deleteResource(Integer id){
        resourceService.deleteResource(id);

        return new ResponseResult(true,200, "根据id删除资源成功", null);
    }

}
