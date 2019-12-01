package com.fh.aop;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(AuthenticateException.class)
	public void authenticateException(AuthenticateException e, HttpServletRequest request, HttpServletResponse response){
		response.setHeader("NOTOKEN",e.getCode().toString());
		response.setHeader("MES",e.getMessage().toString());
	}
}