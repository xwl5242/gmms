package com.zhx.gmms.webserver;

import java.time.Duration;

import org.apache.catalina.Valve;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.valves.AccessLogValve;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.web.embedded.tomcat.ConfigurableTomcatWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * tomcat 定制
 * @author xwl
 */
@Component
public class MyWebServerFactoryCustomizer implements
		WebServerFactoryCustomizer<ConfigurableTomcatWebServerFactory> {

	/**
	 * 定制tomcat
	 */
	@Override
	public void customize(ConfigurableTomcatWebServerFactory factory) {
		TomcatServletWebServerFactory tomcat = (TomcatServletWebServerFactory) factory;
		//定义404 500错误页面,添加错误页
		ErrorPage e404 = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404.html");
		ErrorPage e500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500.html");
		tomcat.addErrorPages(e404,e500);//添加错误页
		//设置session的相关,默认超时时间30分钟
		Session session = new Session();
		session.setTimeout(Duration.ofMinutes(20));
		tomcat.setSession(session);
		//设置容器连接
		tomcat.addConnectorCustomizers(new MyTomcatConnectorCustomizer());
		//设置访问日志
		tomcat.addEngineValves(setAccessLogValve());
	}

	private Valve setAccessLogValve() {
		AccessLogValve log = new AccessLogValve();
		log.setDirectory("f:/tmp/");//设置日志的路径
		log.setPrefix("gmms-");//设置日志名称前缀
		return log;
	}

}

/**
 * 自定义的tomcat连接
 * @author xwl
 */
class MyTomcatConnectorCustomizer implements TomcatConnectorCustomizer{

	@Override
	public void customize(Connector connector) {
		Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
		protocol.setConnectionTimeout(5000);//设置请求超时时间
		protocol.setMaxConnections(100);//设置最大连接数
	}
	
}
