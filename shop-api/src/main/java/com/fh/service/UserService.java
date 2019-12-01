package com.fh.service;

import com.fh.model.UserVo;
import com.fh.model.bean.UserBean;

public interface UserService {

    UserBean queryUserByUsername(String username);

    void updateUser(UserBean userBean);
}
