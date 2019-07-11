package com.mooyle.service;

import com.mooyle.entity.mapper.GoodsMapper;
import com.mooyle.entity.model.SeckillGoods;
import com.mooyle.entity.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    public List<GoodsVo> listGoodsVo(){
        return goodsMapper.listGoodsVo();
    }

    public GoodsVo getGoodsVoByGoodsId(Long id) {
        return goodsMapper.getGoodsVoByGoodsId(id);
    }

    public void reduceStock(GoodsVo goodsVo) {
        SeckillGoods g = new SeckillGoods();
        g.setGoodsId(goodsVo.getId());
        goodsMapper.reduceStock(g);
    }
}
