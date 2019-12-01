package com.fh.service.Impl;

import com.fh.dao.UserDao;
import com.fh.model.UserVo;
import com.fh.model.bean.UserBean;
import com.fh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserBean queryUserByUsername(String username) {
        return userDao.queryUserByUsername(username);
    }

    @Override
    @Transactional
    public void updateUser(UserBean userBean) {
        userDao.updateById(userBean);
    }
}
