package com.myLagou.dao;

import com.myLagou.entity.PromotionSpace;

import java.util.List;

/**
 * @author zhy
 * @create 2022-08-25 14:10
 */
public interface PromotionSpaceMapper {

    /*获取所有广告位*/
    public List<PromotionSpace> findAllPromotionSpace();

    /*新增广告位*/
    public void savePromotionSpace(PromotionSpace promotionSpace);

    /*根据ID查询广告位*/
    public PromotionSpace findPromotionSpaceById(int id);

    /*根据ID修改广告位*/
    public void updatePromotion(PromotionSpace promotionSpace);
}
