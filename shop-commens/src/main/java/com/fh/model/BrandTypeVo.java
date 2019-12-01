package com.fh.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BrandTypeVo implements Serializable {

    private Integer id;

    private Integer pid;

    private String name;

    private Integer status;

    private Integer iid;

    private String brands;//全部

    private List<BrandVo> brands3 = new ArrayList<BrandVo>();

    private List<String> imgs  = new ArrayList<String>();

    public List<BrandVo> getBrands3() {
        return brands3;
    }

    public void setBrands3(List<BrandVo> brands3) {
        this.brands3 = brands3;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    public String getBrands() {
        return brands;
    }

    public void setBrands(String brands) {
        this.brands = brands;
    }

    public Integer getIid() {
        return iid;
    }

    public void setIid(Integer iid) {
        this.iid = iid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
