package com.mooyle.application.service;

import com.mooyle.entity.mapper.OrderInfoMapper;
import com.mooyle.entity.mapper.SeckillOrderMapper;
import com.mooyle.entity.model.OrderInfo;
import com.mooyle.entity.model.SeckillOrder;
import com.mooyle.entity.model.SeckillUser;
import com.mooyle.entity.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class OrderService {

    @Autowired
    OrderInfoMapper orderInfoMapper;

    @Autowired
    SeckillOrderMapper seckillOrderMapper;

    public SeckillOrder getSeckillOrderByUserIdGoodsId(long userId, long goodsId) {
        return orderInfoMapper.seckillOrderByUserIdGoodsId(userId, goodsId);
    }

    public OrderInfo createOrder(SeckillUser user, GoodsVo goodsVo) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goodsVo.getId());
        orderInfo.setGoodsName(goodsVo.getGoodsName());
        orderInfo.setGoodsPrice(new BigDecimal(goodsVo.getSeckillPrice()));
        orderInfo.setOrderChannel(new Integer(1).byteValue());
        orderInfo.setStatus(new Integer(0).byteValue());
        orderInfo.setUserId(user.getId());
        long orderId = orderInfoMapper.insert(orderInfo);
        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setGoodsId(goodsVo.getId());
        seckillOrder.setOrderId(orderId);
        seckillOrder.setUserId(user.getId());
        seckillOrderMapper.insert(seckillOrder);
        return orderInfo;
    }
}
