package com.fh.controller;

import com.fh.model.PageBean;
import com.fh.model.ProductVo;
import com.fh.model.bean.ProductBean;
import com.fh.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pro")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ProductVo queryProductPage(ProductVo productVo){
        productVo =  productService.queryProPage(productVo);
        return productVo;
    }

    @RequestMapping(value = "queryByIdPro",method = RequestMethod.GET)
    public  ProductBean queryByIdPro(Integer id){
        ProductBean productBean = productService.queryById(id);
        return productBean;
    }


}
