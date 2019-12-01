package com.fh.model.bean;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CartBean implements Serializable {

        private Integer productId;//商品id

        private String productName;//商品名称

        private String mainImg;//主图片

        private BigDecimal price;//价格

        private Integer count;//数量

        private BigDecimal subtotal;//小计

        private Boolean isChecked;//是否被选中

}
