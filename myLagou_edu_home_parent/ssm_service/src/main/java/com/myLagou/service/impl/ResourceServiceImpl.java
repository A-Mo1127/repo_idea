package com.myLagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myLagou.dao.ResourceMapper;
import com.myLagou.entity.Resource;
import com.myLagou.entity.ResourceVo;
import com.myLagou.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author zhy
 * @create 2022-08-28 9:54
 */
@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private ResourceMapper resourceMapper;


    @Override
    public PageInfo<Resource> findAllResourceByPage(ResourceVo resourceVo) {
        //分页查询   要借助PageHelper将分页的数据封装进去，比如当前页，每页数量等
        PageHelper.startPage(resourceVo.getCurrentPage(), resourceVo.getPageSize());
        System.out.println(resourceVo.getCurrentPage());

        List<Resource> resourceList = resourceMapper.findAllResourceByPage(resourceVo);
        PageInfo<Resource> pageInfo = new PageInfo<>(resourceList);

        return pageInfo;
    }

    @Override
    public void saveResource(Resource resource) {
        //封装数据

        Date date = new Date();
        resource.setCreatedTime(date);
        resource.setUpdatedTime(date);

        resource.setCreatedBy("system");
        resource.setUpdatedBy("system");

        resourceMapper.saveResource(resource);

    }

    @Override
    public void updateResource(Resource resource) {
        //封装数据
        resource.setUpdatedTime(new Date());

        resourceMapper.updateResource(resource);
    }

    @Override
    public void deleteResource(Integer id) {
        resourceMapper.deleteResource(id);
    }

}
