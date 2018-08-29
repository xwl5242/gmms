package com.zhx.gmms.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;


public class WebInterceptor implements HandlerInterceptor {
	
	private Logger logger = LoggerFactory.getLogger(WebInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
//		logger.info("web端登录拦截器..."+request.getRequestURL().toString());
//		HttpSession session = request.getSession();
//		SysUser loginUser = (SysUser) session.getAttribute(Const.SESSION_USER);
//		if(null==loginUser){
//			logger.info("还没登录...");
//			response.sendRedirect(request.getContextPath()+"index");
//			return false;
//		}
		return true;
	}
	
}
