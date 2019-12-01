package com.fh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.model.PageBean;
import com.fh.model.ProductVo;
import com.fh.model.bean.ProductBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends BaseMapper<ProductVo> {

    Integer queryCount(ProductVo productVo);

    List<ProductVo>  queryProPage(ProductVo productVo);

    ProductBean queryById(Integer id);



}
