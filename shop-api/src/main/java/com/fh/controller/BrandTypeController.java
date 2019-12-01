package com.fh.controller;

import com.fh.model.BrandTypeVo;
import com.fh.model.BrandVo;
import com.fh.service.BrandService;
import com.fh.service.BrandTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("brandType")
@CrossOrigin
public class BrandTypeController  {

    @Autowired
    private BrandTypeService brandTypeService;

    @Autowired
    private BrandService brandService;


    @RequestMapping(value = "queryBrandTypeAll",method = RequestMethod.GET)
    public List<BrandTypeVo> queryBrandTypeAll(){
        List<BrandTypeVo> brandTypeVos = brandTypeService.queryBrandTypeAll();
        return brandTypeVos;
    }


}
