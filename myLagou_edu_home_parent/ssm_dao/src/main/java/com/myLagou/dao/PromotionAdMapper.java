package com.myLagou.dao;

import com.myLagou.entity.PromotionAd;

import java.util.List;

/**
 * @author zhy
 * @create 2022-08-25 16:26
 */
public interface PromotionAdMapper {
    /*分页查询所有广告信息*/
    public List<PromotionAd> findAllPromotionAdByPage();


    /*新建广告*/
    public void savePromotionAd(PromotionAd promotionAd);

    /*回显广告信息*/
    public PromotionAd findPromotionAdById(int id);

    /*修改广告*/
    public void updatePromotionAd(PromotionAd promotionAd);


    /*广告的上下线状态修改*/
    public void updatePromotionAdStatus(PromotionAd promotionAd);

}
