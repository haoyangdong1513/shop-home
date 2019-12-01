package com.fh.service.Impl;

import com.fh.dao.BrandDao;
import com.fh.model.BrandVo;
import com.fh.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandDao brandDao;

    @Override
    public List<BrandVo> queryBrandAll() {
        return brandDao.queryBrandAll();
    }

    @Override
    public BrandVo queryBrandPage(BrandVo brandVo) {
        Integer total = brandDao.queryCount(brandVo);
        brandVo.setTotal(total);
        brandVo.setPageTotal((total-1)/brandVo.getPageSize()+1);

        List<BrandVo> users = brandDao.queryBrandPage(brandVo);
        brandVo.setList(users);

        return brandVo;
    }
}
