package com.fh.controller;


import com.fh.model.BrandVo;
import com.fh.service.BrandService;
import com.fh.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("brand")
@CrossOrigin
public class BrandController {

    @Autowired
    private BrandService brandService;

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("queryBrandPage")
    public BrandVo queryBrandPage(BrandVo brandVo){
        if (brandVo.getBrandtype() != null){
            String s = String.valueOf(brandVo.getBrandtype());
            if(redisUtil.hasKey(s)){
                brandVo = (BrandVo) redisUtil.get(s);
            }else{
                brandVo = brandService.queryBrandPage(brandVo);
                redisUtil.set(s,brandVo);
            }
        }
         return brandVo;
    }

}
