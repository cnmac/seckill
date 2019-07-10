package com.mooyle.entity.mapper;

import com.mooyle.entity.model.Goods;
import com.mooyle.entity.vo.GoodsVo;

import java.util.List;

public interface GoodsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKeyWithBLOBs(Goods record);

    int updateByPrimaryKey(Goods record);

    List<GoodsVo> listGoodsVo();

    GoodsVo getGoodsVoByGoodsId(Long goodsId);
}