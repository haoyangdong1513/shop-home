package com.fh.controller;

import com.fh.annotation.LoginCheckToken;
import com.fh.model.bean.CartBean;
import com.fh.model.bean.UserBean;
import com.fh.service.CartService;
import com.fh.util.RedisUtil;
import com.fh.util.ResponseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("cart")
@CrossOrigin(maxAge = 3600,exposedHeaders = "NOTOKEN,MES")
public class CartController {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private CartService cartService;

    /**
     * 添加购物车
     * @param request
     * @param proId
     * @return
     */
    @PostMapping
    @LoginCheckToken
    public ResponseServer checkLogin(HttpServletRequest request, String proId){
        String phone = (String) request.getAttribute("phone");
        ResponseServer responseServer = cartService.addCart(proId,phone);

        return  responseServer;
    }

    /**
     * 跳转购物车的展示页面
     * @return
     */
    @GetMapping
    @LoginCheckToken
    public ResponseServer toCart(){
        return ResponseServer.success();
    }

    /**
     * 查询购物车
     * @param request
     * @return
     */
    @PutMapping
    @LoginCheckToken
    public ResponseServer queryCart(HttpServletRequest request){
        String phone = (String) request.getAttribute("phone");
        ResponseServer responseServer = cartService.queryCart(phone);
        return responseServer;
    }

    /**
     * 复选框全选全不选计算价格
     * @return
     */
    @PutMapping("/imputed")
    @LoginCheckToken
    public ResponseServer imputed(HttpServletRequest request,Boolean fig){
        String phone = (String) request.getAttribute("phone");
        cartService.imputeds(phone,fig);
        return ResponseServer.success();
    }

    /**
     * 复选框单个商品方法
     * @param request
     * @param id
     * @return
     */
    @GetMapping("/upProCheck")
    @LoginCheckToken
    public ResponseServer upProCheck(HttpServletRequest request,Integer id){
        String phone = (String) request.getAttribute("phone");
        cartService.upProCheck(phone,id);
        return ResponseServer.success();
    }

    /**
     * 单个商品台修改数量
     * @param request
     * @param id
     * @return
     */
    @GetMapping("/addCount")
    @LoginCheckToken
    public ResponseServer addCount(HttpServletRequest request,Integer id,Integer sta){
        String phone = (String) request.getAttribute("phone");
        cartService.addCount(phone,id,sta);
        return  ResponseServer.success();
    }

    /**
     * 手动修改购买个数的输入框
     * @param request
     * @param id
     * @param val
     * @return
     */
    @GetMapping("/upCount")
    @LoginCheckToken
    public ResponseServer upCount(HttpServletRequest request,Integer id,Integer val){
        String phone = (String) request.getAttribute("phone");
        cartService.upCount(phone,id,val);
        return  ResponseServer.success();
    }

    /**
     * 删除单个商品
     * @param request
     * @param id
     * @return
     */
    @GetMapping("/delPro")
    @LoginCheckToken
    public ResponseServer delPro(HttpServletRequest request,Integer id){
        String phone = (String) request.getAttribute("phone");
        cartService.delPro(phone,id);
        return ResponseServer.success();
    }

    /**
     * 获取用户购物车的个数
     * @param request
     * @return
     */
    @GetMapping("/cartCount")
    @LoginCheckToken
    public ResponseServer cartCount(HttpServletRequest request){
        String phone = (String) request.getAttribute("phone");
        ResponseServer responseServer = cartService.cartCount(phone);
        return responseServer;
    }


}
