package com.fh.service.Impl;

import com.fh.dao.BrandDao;
import com.fh.dao.BrandTypeDao;
import com.fh.model.BrandTypeVo;
import com.fh.model.BrandVo;
import com.fh.service.BrandTypeService;
import com.fh.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandTypeServiceImpl implements BrandTypeService {

    @Autowired
    private BrandTypeDao brandTypeDao;

    @Autowired
    private BrandDao brandDao;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public List<BrandTypeVo> queryBrandTypeAll() {
        List<BrandTypeVo>  brandTypeVos = null;
        List<BrandVo> brandVos = null;
        if(redisUtil.hasKey("brandTypeVos") && redisUtil.hasKey("brandVos")){//判断这个值是否存在
            brandTypeVos = (List<BrandTypeVo>) redisUtil.get("brandTypeVos");
            brandVos = (List<BrandVo>) redisUtil.get("brandVos");
        }else{
             brandTypeVos = brandTypeDao.queryBrandTypeAll();
             brandVos = brandDao.queryBrandAll();
             redisUtil.set("brandTypeVos", brandTypeVos);
             redisUtil.set("brandVos", brandVos);
        }
        for (BrandTypeVo brandTypes:brandTypeVos){
            for (BrandVo brandVo : brandVos){
                if (brandVo.getBrandtype() != null){
                    Integer integer = Integer.valueOf(brandVo.getBrandtype());
                    Integer integer1 = Integer.valueOf(brandTypes.getId());
                    if (integer == integer1){
                        brandTypes.getBrands3().add(brandVo);
                        brandTypes.getImgs().add(brandVo.getImgurl());
                    }
                }
            }
        }
        return brandTypeVos;
    }

}
