package com.fh.service;

import com.fh.util.ResponseServer;

public interface CartService {

    ResponseServer addCart(String proId,String phone);

    ResponseServer queryCart(String phone);

    void imputeds(String phone,Boolean fig);

    void upProCheck(String phone,Integer id);

    void addCount(String phone, Integer id,Integer sta);

    void upCount(String phone, Integer id, Integer val);

    void delPro(String phone, Integer id);

    ResponseServer cartCount(String phone);
}
