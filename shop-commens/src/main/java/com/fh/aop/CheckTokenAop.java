package com.fh.aop;


import com.fh.util.JwtUtils;
import com.fh.util.ResponseServer;
import com.fh.util.ServerEnum;
import io.jsonwebtoken.Claims;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class CheckTokenAop {

    @Pointcut("@annotation(com.fh.annotation.LoginCheckToken)")
    public void RoleValidAroundPointCut(){}

    @Around("RoleValidAroundPointCut()")
    public Object aroundMethod(ProceedingJoinPoint joinPoint){

        //获取Token
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getHeader("token");

        //验证token
        ResponseServer responseServer = JwtUtils.resolverToken(token);

        //验证失败
        if (!responseServer.getCode().toString().equals("200")){
            throw new AuthenticateException(ServerEnum.TOKEN_TIMEOUT);
        }

        Claims claims =(Claims) responseServer.getData();
        String phone =(String) claims.get("phone");
        request.setAttribute("phone",phone);


        //注意：使用环绕增强around时如果不显示的使用proced方法的话，目标方法便不会执行
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;

    }

}
