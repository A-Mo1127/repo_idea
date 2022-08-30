package com.myLagou.service;

import com.github.pagehelper.PageInfo;
import com.myLagou.entity.PromotionAd;
import com.myLagou.entity.PromotionAdVo;

import java.util.List;

/**
 * @author zhy
 * @create 2022-08-25 16:39
 */
public interface PromotionAdService {
    /*分页查询所有广告信息*/
    public PageInfo<PromotionAd> findAllPromotionAdByPage(PromotionAdVo promotionAdVo);


    /*新建广告*/
    public void savePromotionAd(PromotionAd promotionAd);

    /*回显广告信息*/
    public PromotionAd findPromotionAdById(int id);

    /*修改广告*/
    public void updatePromotionAd(PromotionAd promotionAd);

    /*广告的上下线状态修改*/
    public void updatePromotionAdStatus(int id, int status);

}
