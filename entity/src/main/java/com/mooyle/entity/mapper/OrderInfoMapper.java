package com.mooyle.entity.mapper;

import com.mooyle.entity.model.OrderInfo;
import com.mooyle.entity.model.SeckillOrder;

public interface OrderInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderInfo record);

    int insertSelective(OrderInfo record);

    OrderInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderInfo record);

    int updateByPrimaryKey(OrderInfo record);

    SeckillOrder seckillOrderByUserIdGoodsId(Long userId, Long goodsId);
}