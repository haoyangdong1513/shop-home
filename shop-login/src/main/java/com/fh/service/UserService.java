package com.fh.service;

import com.fh.model.UserVo;
import com.fh.model.bean.UserBean;


public interface UserService  {

    UserBean queryByPhone(String phone);

    void  addUser(UserBean userBean);

}
