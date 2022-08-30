package com.myLagou.service;

import com.github.pagehelper.PageInfo;
import com.myLagou.entity.Resource;
import com.myLagou.entity.ResourceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhy
 * @create 2022-08-28 9:53
 */
public interface ResourceService {

    /*资源分页和多条件查询*/
    public PageInfo<Resource> findAllResourceByPage(ResourceVo resourceVo);

    /*添加资源*/
    public void saveResource(Resource resource);

    /*更新资源*/
    public void updateResource(Resource resource);

    /*根据id删除资源*/
    public void deleteResource(Integer id);
}
