package com.myLagou.service.impl;

import com.myLagou.dao.PromotionSpaceMapper;
import com.myLagou.entity.PromotionSpace;
import com.myLagou.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author zhy
 * @create 2022-08-25 14:18
 */
@Service
public class PromotionSpaceServiceImpl implements PromotionSpaceService {
    @Autowired
    private PromotionSpaceMapper promotionSpaceMapper;

    @Override
    public List<PromotionSpace> findAllPromotionSpace() {
        List<PromotionSpace> spaceList = promotionSpaceMapper.findAllPromotionSpace();

        return spaceList;
    }

    /*新增广告位*/
    @Override
    public void savePromotionSpace(PromotionSpace promotionSpace) {
        //补全信息
        promotionSpace.setSpaceKey(UUID.randomUUID().toString());//不重复即可
        Date date = new Date();
        promotionSpace.setCreateTime(date);
        promotionSpace.setUpdateTime(date);
        promotionSpace.setIsDel(0);

        promotionSpaceMapper.savePromotionSpace(promotionSpace);
    }

    @Override
    public PromotionSpace findPromotionSpaceById(int id) {
        PromotionSpace promotionSpace = promotionSpaceMapper.findPromotionSpaceById(id);

        return promotionSpace;
    }

    @Override
    public void updatePromotion(PromotionSpace promotionSpace) {
        //补全数据
        Date date = new Date();
        promotionSpace.setUpdateTime(date);

        promotionSpaceMapper.updatePromotion(promotionSpace);
    }


}
