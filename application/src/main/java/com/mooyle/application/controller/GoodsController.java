package com.mooyle.application.controller;

import com.alibaba.fastjson.JSONObject;
import com.mooyle.application.result.CodeMsg;
import com.mooyle.application.result.Result;
import com.mooyle.application.service.OrderService;
import com.mooyle.application.service.SeckillService;
import com.mooyle.entity.model.OrderInfo;
import com.mooyle.entity.model.SeckillOrder;
import com.mooyle.entity.model.SeckillUser;
import com.mooyle.entity.vo.GoodsVo;
import com.mooyle.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mooyle.application.result.CodeMsg.SECKILL_FAIL;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Autowired
    SeckillService seckillService;

    @RequestMapping("/list")
    @ResponseBody
    public Result<List<GoodsVo>> listGoods(SeckillUser user) {
        if(user == null){
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        List<GoodsVo> goodsVo = goodsService.listGoodsVo();
        return Result.success(goodsVo);
    }

    @GetMapping("/detail/{goodsId}")
    @ResponseBody
    public Result<JSONObject> goodsDetail(SeckillUser user, @PathVariable("goodsId") long goodsId){
        if(user == null){
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        GoodsVo goodsVo = goodsService.getGoodsVoByGoodsId(goodsId);
        long startDate = goodsVo.getStartDate().getTime();
        long endDate = goodsVo.getEndDate().getTime();
        long now = System.currentTimeMillis();
        int seckillState = 0;
        long remainSecond = 0;
        if (now < startDate) { //倒计时
            seckillState = 0;
            remainSecond = (startDate - now) / 1000;
        } else if (now > endDate) { //结束
            seckillState = 2;
            remainSecond = -1;
        } else { // 进行中
            seckillState = 1;
            remainSecond = 0;
        }
        JSONObject res = new JSONObject();
        res.put("goods", goodsVo);
        res.put("seckillState", seckillState);
        res.put("remainSecond", remainSecond);
        return Result.success(res);
    }


    @GetMapping("/do_seckill")
    @ResponseBody
    public Result<JSONObject> doSeckill(SeckillUser user, @RequestParam("goodsId") long goodsId) {
        if(user == null){
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        // 判断库存
        GoodsVo goodsVo = goodsService.getGoodsVoByGoodsId(goodsId);
        Integer stockCount = goodsVo.getStockCount();
        if (stockCount <= 0) {
            return Result.error(CodeMsg.STOCK_ERROR);
        }
        SeckillOrder seckillOrder = orderService.getSeckillOrderByUserIdGoodsId(user.getId(), goodsId);
        if(seckillOrder != null){
            return Result.error(SECKILL_FAIL);
        }
        JSONObject res = new JSONObject();
        OrderInfo orderInfo = seckillService.seckill(user, goodsVo);
        res.put("order_info", orderInfo);
        return Result.success(res);
    }


}
