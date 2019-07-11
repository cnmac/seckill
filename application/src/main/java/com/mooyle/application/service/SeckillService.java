package com.mooyle.application.service;

import com.mooyle.entity.model.OrderInfo;
import com.mooyle.entity.model.SeckillUser;
import com.mooyle.entity.vo.GoodsVo;
import com.mooyle.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeckillService {

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    public OrderInfo seckill(SeckillUser user, GoodsVo goodsVo) {
        //减库存
        goodsService.reduceStock(goodsVo);
        //创建订单
        return orderService.createOrder(user, goodsVo);
    }
}
