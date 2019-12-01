package com.fh.model;

import com.alibaba.fastjson.JSON;
import com.fh.util.ResponseServer;
import com.fh.util.ServerEnum;
import io.jsonwebtoken.*;
import sun.misc.BASE64Encoder;

import java.util.HashMap;
import java.util.Map;

public class BBBB {

    /**
     * 生成token
     * @param map
     * @return
     */
    public  static  String createToken(Map<String,Object> map){
        Map<String,Object> headerMap = new HashMap<String, Object>();
        headerMap.put("alg","HS256");
        headerMap.put("typ","JWT");

        Long iat = System.currentTimeMillis();
        System.out.println(iat);
        map.put("exp",iat+600001);
        map.put("iat",iat);
        String token = Jwts.builder()
                .setHeader(headerMap)
                .setPayload(JSON.toJSONString(map))
                .signWith(SignatureAlgorithm.HS256,getSecretKey("dongSir"))
                .compact();
        return token;
    }
    private  static String getSecretKey(String key){
        return new BASE64Encoder().encode(key.getBytes());
    }

    public static ResponseServer resolverToken(String token){
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(getSecretKey("dongSir"))
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            System.out.println("token超时，token失效了");
            return ResponseServer.error(ServerEnum.TOKEN_TIMEOUT);
        }  catch (SignatureException e) {
            System.out.println("token解析失败");
            return ResponseServer.error(ServerEnum.SAFETY_ERROR);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return ResponseServer.success();

    }


    public static void main(String[] args) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("name","阿斯加德");
        String token = createToken(map);
        System.out.println(token);

    }

}
