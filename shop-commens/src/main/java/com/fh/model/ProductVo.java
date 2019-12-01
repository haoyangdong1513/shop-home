package com.fh.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductVo  extends PageBean<ProductVo>{

    private Integer id;
    private Integer brandId;//品牌id
    private String name;//商品名称
    private String subtitle;//宣传标题
    private String mianImg;//主图片
    private String subImgs;//副图片
    private String detail;//商品描述
    private BigDecimal price;//价格
    private Integer stock;//库存
    private Integer status;//状态
    private Date createTime;
    private Date updateTime;
    private Integer typeId;





}
