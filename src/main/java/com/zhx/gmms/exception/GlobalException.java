package com.zhx.gmms.exception;

import javax.servlet.ServletException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常
 * @author xwl
 */
@ControllerAdvice
public class GlobalException {

	@ExceptionHandler(ServletException.class)
	@ResponseBody
	public String exception(Exception e){
		return "servletException";
	}
}
