package com.fh.service;

import com.fh.model.PageBean;
import com.fh.model.ProductVo;
import com.fh.model.bean.ProductBean;

public interface ProductService {


    ProductVo queryProPage(ProductVo productVo);

    ProductBean queryById(Integer id);

}
