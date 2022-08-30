package com.myLagou.controller;

import com.myLagou.entity.PromotionSpace;
import com.myLagou.entity.ResponseResult;
import com.myLagou.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhy
 * @create 2022-08-25 14:22
 */
@RestController
@RequestMapping("/PromotionSpace")
public class PromotionSpaceController {
    @Autowired
    private PromotionSpaceService promotionSpaceService;

    /*查找所有广告位*/
    @RequestMapping("/findAllPromotionSpace")
    public ResponseResult findAllPromotionSpace(){

        List<PromotionSpace> allPromotionSpace = promotionSpaceService.findAllPromotionSpace();

        ResponseResult result = new ResponseResult(true, 200, "查找所有广告位成功", allPromotionSpace);
        return result;
    }

    /*新增和修改广告位*/
    @RequestMapping("/saveOrUpdatePromotionSpace")
    public ResponseResult saveOrUpdatePromotionSpace(@RequestBody PromotionSpace promotionSpace){
        if (promotionSpace.getId() == null){//新增
            promotionSpaceService.savePromotionSpace(promotionSpace);

            ResponseResult result = new ResponseResult(true, 200, "新增广告位成功", null);
            return result;
        }else {//修改
            promotionSpaceService.updatePromotion(promotionSpace);
            ResponseResult result = new ResponseResult(true, 200, "修改广告位成功", null);
            return result;
        }
    }

    /*根据ID查询广告位*/
    @RequestMapping("/findPromotionSpaceById")
    public ResponseResult finPromotionSpaceById(int id){
        PromotionSpace promotionSpace = promotionSpaceService.findPromotionSpaceById(id);

        ResponseResult result = new ResponseResult(true, 200, "根据id查找广告位成功", promotionSpace);
        return result;
    }

}
