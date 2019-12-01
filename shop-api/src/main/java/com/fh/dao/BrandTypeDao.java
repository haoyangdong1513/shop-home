package com.fh.dao;

import com.fh.model.BrandTypeVo;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandTypeDao {

    List<BrandTypeVo>   queryBrandTypeAll();

}
