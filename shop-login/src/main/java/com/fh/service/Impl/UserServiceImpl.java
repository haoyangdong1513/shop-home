package com.fh.service.Impl;

import com.fh.dao.UserDao;
import com.fh.model.UserVo;
import com.fh.model.bean.UserBean;
import com.fh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserBean queryByPhone(String phone) {
        return userDao.queryByPhone(phone);
    }

    public void addUser(UserBean userBean){
        userDao.insert(userBean);
    }

}
