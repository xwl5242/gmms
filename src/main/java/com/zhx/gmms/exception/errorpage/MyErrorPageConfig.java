package com.zhx.gmms.exception.errorpage;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * 自定义错误页面
 * @author xwl
 */
@Component
public class MyErrorPageConfig implements ErrorPageRegistrar {

	@Override
	public void registerErrorPages(ErrorPageRegistry registry) {
		ErrorPage e404 = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404.html");
		ErrorPage e500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500.html");
		registry.addErrorPages(e404,e500);
	}

}
