package com.fh.dao;

import com.fh.model.BrandVo;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandDao {

    List<BrandVo> queryBrandAll();

    List<BrandVo> queryBrandByTypeId(Integer typeId);

    Integer queryCount(BrandVo brandVo);
    List<BrandVo> queryBrandPage(BrandVo brandVo);


}
