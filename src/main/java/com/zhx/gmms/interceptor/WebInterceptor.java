package com.zhx.gmms.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import com.zhx.gmms.frame.Const;
import com.zhx.gmms.modules.sys.user.bean.SysUser;


public class WebInterceptor implements HandlerInterceptor {
	
	private Logger logger = LoggerFactory.getLogger(WebInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		logger.info("web端登录拦截器...请求地址："+request.getRequestURL().toString());
		HttpSession session = request.getSession();
		logger.info("session有效期设置："+session.getMaxInactiveInterval()+"s");
		SysUser loginUser = (SysUser) session.getAttribute(Const.SESSION_USER);
		if(null==loginUser){
			logger.info("还没登录...跳转到登录页面");
			response.sendRedirect(request.getContextPath()+"/index");
			return false;
		}
		return true;
	}
	
}
