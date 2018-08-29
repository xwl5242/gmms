package com.zhx.gmms.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// web端登录拦截器注册
//		registry.addInterceptor(new WebInterceptor())
//				.addPathPatterns("/**")
//				.excludePathPatterns("/index", "/loginIn", "/captcha",
//						"/error", "/admui/**", "/static/**",
//						"/templates/error/404.html", "/templates/error/500.html");
		// pjax请求拦截器注册
		registry.addInterceptor(new PjaxInterceptor()).addPathPatterns("/**")
				.excludePathPatterns("/index", "/admui/**", "/static/**");
	}

}
