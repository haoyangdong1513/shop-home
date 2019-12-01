package com.fh.model.bean;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductBean {
    private Integer id;
    private Integer brandId;//品牌id
    private String name;//商品名称
    private String subtitle;//宣传标题
    private String mainImg;//主图片
    private String subImgs;//副图片
    private String detail;//商品描述
    private BigDecimal price;//价格
    private Integer stock;//库存
    private Integer status;//状态
    private Date createTime;
    private Date updateTime;
    private Integer typeId;
}
