package com.fh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.model.UserVo;
import com.fh.model.bean.UserBean;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao  extends BaseMapper<UserBean> {

    UserBean queryUserByUsername(String username);

}
