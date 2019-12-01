package com.fh.controller;

import com.alibaba.fastjson.JSONObject;
import com.fh.model.UserVo;
import com.fh.model.bean.UserBean;
import com.fh.service.UserService;
import com.fh.util.CheckCode;
import com.fh.util.RedisUtil;
import com.fh.util.ResponseServer;
import com.fh.util.ServerEnum;
import org.apache.catalina.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("getCode")
public class GetCodeController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @PostMapping
    public ResponseServer getCodes( UserBean userBean2) throws Exception {
        if (StringUtils.isBlank(userBean2.getPhone())){
            return ResponseServer.error(ServerEnum.PHONE_ISNULL);
        }
            JSONObject jsonObject = CheckCode.sendMessage(userBean2.getPhone());
            String code = jsonObject.getString("code");
            if (!code.equals("200")){
                return ResponseServer.error(ServerEnum.PHONE_ISNULL);
            }
            //userService.addUser(userBean2);
            String obj = jsonObject.getString("obj");
            redisUtil.set(userBean2.getPhone(),obj,300);
        return  ResponseServer.success();
    }

}
