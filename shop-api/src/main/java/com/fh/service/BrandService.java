package com.fh.service;

import com.fh.model.BrandVo;

import java.util.List;

public interface BrandService {

    List<BrandVo> queryBrandAll();

    BrandVo queryBrandPage(BrandVo brandVo);

}
