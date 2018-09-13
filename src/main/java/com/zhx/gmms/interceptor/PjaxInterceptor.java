package com.zhx.gmms.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 是否是pjax请求 的拦截器。根据不同类型的请求方式返回不同的模板
 * @author xwl
 */
public class PjaxInterceptor extends HandlerInterceptorAdapter {

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		if (modelAndView != null) {
			//值为true表示pjax请求，这是重点
            boolean isPajx = Boolean.parseBoolean(request.getHeader("X-PJAX"));
            ModelMap model = modelAndView.getModelMap();
            String uri = request.getRequestURI().toString();
            if (isPajx) {
            	//指定pjax请求时使用的模版
                model.addAttribute("FP", "/layout/blank.html");
            }else{
            	if("/system/account/log".equals(uri)||"/system/account/password".equals(uri)){
            		model.addAttribute("FP", "/layout/subAccount.html");
            	}else{
            		model.addAttribute("FP", "/layout/default.html");
            	}
            }
        }

	}

}