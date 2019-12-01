package com.fh.controller;

import com.fh.model.UserVo;
import com.fh.model.bean.UserBean;
import com.fh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("check")
@CrossOrigin
public class CheckController {

    @Autowired
    private UserService userService;

    //登录验证用户是否存在
    @RequestMapping("checklogin")
    public Map<String,Object> checklogin(UserVo userVo, HttpSession session){

        Map<String,Object> map = new HashMap<String, Object>();

        //验证码忽略大小写
            //通过用户在登录页面填写的用户名获取单个用户信息
            UserBean user2 = userService.queryUserByUsername(userVo.getUsername());
            //如果用户名存在
            if(user2 != null){
                //30秒后用户再次登录次数重置
                if(user2.getErrorcount() != null && user2.getErrorcount() >= 3 && (System.currentTimeMillis() - user2.getErrortime().getTime()) >= 1000*30 ){

                    user2.setErrorcount(null);//将用户登录失败次数置空

                    user2.setErrortime(null);//将用户最后登录失败时间置空


                    userService.updateUser(user2);
                }

                //根据用户登录失败次数
                //只有登录失败次数为空或者登录失败次数小于等于3时才会对用户在登录页面输入的密码做校验
                if(user2.getErrorcount() == null || user2.getErrorcount() < 3){
                    //如果密码正确
                    if(user2.getPassword().equals(userVo.getPassword())){

                            user2.setErrorcount(null);//将用户登录失败次数置空

                            user2.setErrortime(null);//将用户最后登录失败时间置空
                            user2.setLogintime(new Date());
                            user2.setLogincount(user2.getLogincount()==null?1:user2.getLogincount()+1);

                            userService.updateUser(user2);

                            session.setAttribute("loginUser",user2);//将登录成功的用户信息放到Session工厂中

                            map.put("status", 1);//用户密码正确
                        }else{
                            //user2.setErrorcount(user2.getErrorcount()==null?1:user2.getErrorcount()+1);
                            //如果密码错误
                            //记录用户登录错误次数     Integer 初始值为 null
                            if(user2.getErrorcount() == null){ //第一次Errorcount的值为 null
                                user2.setErrorcount(1);
                            }else{
                                user2.setErrorcount(user2.getErrorcount()+1);
                            }

                            user2.setErrortime(new Date());//记录用户最后一次登录错误时间

                            userService.updateUser(user2);

                            map.put("status", 2);//用户或密码不正确
                        }
                    }else{
                        map.put("status",4);//用户被冻结
                    }
                }else{
                    map.put("status", 2);//用户或密码不正确
                }
            return map;
    }


}
