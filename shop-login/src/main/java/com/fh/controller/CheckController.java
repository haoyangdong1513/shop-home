package com.fh.controller;

import com.fh.model.bean.UserBean;
import com.fh.service.UserService;
import com.fh.util.JwtUtils;
import com.fh.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("check")
@CrossOrigin
public class CheckController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("demo")
    public void demo(HttpServletRequest request){
        String token = request.getHeader("token");
        System.out.println(token);
    }

    @GetMapping("/{phone}/{code1}")
    public Map<String,Object> checkCode(@PathVariable("phone") String phone, @PathVariable("code1") String yan, HttpServletResponse response){
        Map<String,Object> map = new HashMap<>();
        UserBean loginUser = (UserBean) redisUtil.get("user_"+phone);
        String o = (String) redisUtil.get(phone);
        if(loginUser == null){
            UserBean userBean = userService.queryByPhone(phone);
            if (userBean!= null){
                redisUtil.set("user_"+phone,userBean);

                if(yan.equals(o)){
                    map.put("phone",phone);
                    String token = JwtUtils.createToken(map);
                    map.put("status",1);//允许登录
                    map.put("token",token);
                }else{
                    map.put("status",2);//验证码输入有误
                }
            }else{
                if(yan.equals(o)){
                    UserBean userBean1 = new UserBean();
                    userBean1.setPhone(phone);
                    userBean1.setCreateTime(new Date());
                    userBean1.setCartUUID(UUID.randomUUID().toString().replace("-",""));
                    userService.addUser(userBean1);

                    map.put("phone",phone);
                    String token = JwtUtils.createToken(map);
                    map.put("status",1);//允许登录
                    map.put("token",token);

                }else{
                    map.put("status",2);//验证码输入有误
                }
            }
        }else{
            if(yan.equals(o)){
                map.put("phone",phone);
                String token = JwtUtils.createToken(map);
                map.put("status",1);//允许登录
                map.put("token",token);
            }else{
                map.put("status",2);//验证码输入有误
            }
        }
        return map;
    }

}
