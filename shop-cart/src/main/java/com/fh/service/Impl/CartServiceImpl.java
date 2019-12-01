package com.fh.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fh.model.ProductVo;
import com.fh.model.bean.CartBean;
import com.fh.model.bean.ProductBean;
import com.fh.model.bean.UserBean;
import com.fh.service.CartService;
import com.fh.util.HttpclientUtils;
import com.fh.util.RedisUtil;
import com.fh.util.ResponseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("cartService")
public class CartServiceImpl implements CartService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public ResponseServer addCart(String proId,String phone) {
        UserBean userBean = (UserBean) redisUtil.get("user_" + phone);
        String url = "http://localhost:9003/pro/queryByIdPro";
        Map<String,String> map = new HashMap<>();
        map.put("id",proId);
        String str = HttpclientUtils.doGet(url,map);

        ProductBean productVo = JSON.parseObject(str, ProductBean.class);
        CartBean cart = new CartBean();
        cart.setProductId(productVo.getId());
        cart.setProductName(productVo.getName());
        cart.setMainImg(productVo.getMainImg());
        cart.setPrice(productVo.getPrice());
        cart.setSubtotal(productVo.getPrice());
        cart.setIsChecked(true);
        CartBean hget = (CartBean)redisUtil.hget(userBean.getCartUUID(), String.valueOf(productVo.getId()));
        if(hget!=null){
            cart.setCount(hget.getCount()+1);

            //计算小计
            BigDecimal num1 = new BigDecimal(0.00);
            BigDecimal counts = new BigDecimal(cart.getCount());
            cart.setSubtotal(num1.add(cart.getPrice()).multiply(counts));

        }else{
            cart.setCount(1);
        }
        redisTemplate.opsForHash().put(userBean.getCartUUID(),String.valueOf(productVo.getId()),cart);
        List<CartBean> cartAll = redisTemplate.opsForHash().values(userBean.getCartUUID());
        Integer count  = 0 ;
        for(CartBean cartBean :cartAll){
            count = count+cartBean.getCount();
        }

        return ResponseServer.success(count);
    }

    @Override
    public ResponseServer queryCart(String phone) {
        UserBean userBean = (UserBean) redisUtil.get("user_" + phone);//通过手机号获取用户信息
        Map<String,Object> map = new HashMap<>();
        List<CartBean> cartAll = redisTemplate.opsForHash().values(userBean.getCartUUID());
        BigDecimal num = new BigDecimal("0.00");
        Integer selCount = 0 ;//已选商品的个数
        for (CartBean cart : cartAll){
            BigDecimal num1 = new BigDecimal(0.00);
            BigDecimal counts = new BigDecimal(cart.getCount());
            cart.setSubtotal(num1.add(cart.getPrice()).multiply(counts));
            if(cart.getIsChecked() == true){
                num =   num.add(cart.getSubtotal());
                selCount++;
            }
        }
        map.put("cartAll",cartAll);
        map.put("sum",num);
        map.put("selCount",selCount);
        return ResponseServer.success(map);
    }

    @Override
    public void imputeds(String phone, Boolean fig) {
        UserBean userBean = (UserBean) redisUtil.get("user_" + phone);
        List<CartBean> cartAll = redisTemplate.opsForHash().values(userBean.getCartUUID());
        for (CartBean cart : cartAll){
            CartBean cartBean = (CartBean) redisTemplate.opsForHash().get(userBean.getCartUUID(), String.valueOf(cart.getProductId()));
            if (fig == true){
                cartBean.setIsChecked(true);
            }else{
                cartBean.setIsChecked(false);
            }
            redisTemplate.opsForHash().put(userBean.getCartUUID(),String.valueOf(cartBean.getProductId()),cartBean);
        }
    }

    @Override
    public void upProCheck(String phone,Integer id) {
        UserBean userBean = (UserBean) redisUtil.get("user_" + phone);
        CartBean cartBean = (CartBean) redisTemplate.opsForHash().get(userBean.getCartUUID(), String.valueOf(id));
        cartBean.setIsChecked(!cartBean.getIsChecked());
        redisTemplate.opsForHash().put(userBean.getCartUUID(),String.valueOf(id),cartBean);
    }

    @Override
    public void addCount(String phone, Integer id,Integer sta) {
        UserBean userBean = (UserBean) redisUtil.get("user_" + phone);
        CartBean cartBean = (CartBean) redisTemplate.opsForHash().get(userBean.getCartUUID(), String.valueOf(id));
        if (sta == 2){//加
            cartBean.setCount(cartBean.getCount()+1);
            redisTemplate.opsForHash().put(userBean.getCartUUID(),String.valueOf(id),cartBean);
        }
        if (sta == 1){//减
            if (cartBean.getCount() == 0){
                cartBean.setCount(0);
                redisTemplate.opsForHash().put(userBean.getCartUUID(),String.valueOf(id),cartBean);
            }else{
                cartBean.setCount(cartBean.getCount()-1);
                redisTemplate.opsForHash().put(userBean.getCartUUID(),String.valueOf(id),cartBean);
            }
        }
    }

    @Override
    public void upCount(String phone, Integer id, Integer val) {
        UserBean userBean = (UserBean) redisUtil.get("user_" + phone);
        CartBean cartBean = (CartBean) redisTemplate.opsForHash().get(userBean.getCartUUID(), String.valueOf(id));
        cartBean.setCount(val);
        redisTemplate.opsForHash().put(userBean.getCartUUID(),String.valueOf(id),cartBean);
    }

    @Override
    public void delPro(String phone, Integer id) {
        UserBean userBean = (UserBean) redisUtil.get("user_" + phone);
        redisTemplate.opsForHash().delete(userBean.getCartUUID(),String.valueOf(id));
    }

    @Override
    public ResponseServer cartCount(String phone) {
        UserBean userBean = (UserBean) redisUtil.get("user_" + phone);
        List<CartBean> list = redisTemplate.opsForHash().values(userBean.getCartUUID());
        Integer count = 0 ;
        for (CartBean cartBean :list){
            count +=cartBean.getCount();
        }
        return ResponseServer.success(count);
    }
}
