package com.fh.service.Impl;

import com.fh.dao.ProductDao;
import com.fh.model.PageBean;
import com.fh.model.ProductVo;
import com.fh.model.bean.ProductBean;
import com.fh.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public ProductVo queryProPage(ProductVo productVo) {
        Integer total = productDao.queryCount(productVo);
        productVo.setTotal(total);

        List<ProductVo> productVos = productDao.queryProPage(productVo);
        productVo.setList(productVos);

        return productVo;
    }

    @Override
    public ProductBean queryById(Integer id) {
        return productDao.queryById(id);
    }
}
