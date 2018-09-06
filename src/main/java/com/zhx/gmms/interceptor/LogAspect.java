package com.zhx.gmms.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zhx.gmms.modules.sys.log.bean.SysLog;
import com.zhx.gmms.modules.sys.log.service.SysLogService;
import com.zhx.gmms.utils.IPUtil;
import com.zhx.gmms.utils.UUIDGenerator;

/**
 * 日志aop
 * @author xwl
 */
@Aspect  
@Component  
public class LogAspect {  
	
	private Logger logger = LoggerFactory.getLogger(LogAspect.class);
	
	@Autowired
	private SysLogService logService;
	
	@Value("${open-log}")
	private boolean openLog;
	
    @Pointcut("execution(public * com.zhx.gmms.modules.*..*Controller.*(..))")  
    public void webLog(){}  
  
    @Before("webLog()")  
    public void deBefore(JoinPoint joinPoint) throws Throwable {  
    	if(openLog){
    		// 接收到请求，记录请求内容  
    		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();  
    		HttpServletRequest request = attributes.getRequest();  
    		// 记录下请求内容  
    		String reqUri = request.getRequestURI();//访问的URL
    		String remoteIp = IPUtil.getIpAddr(request);//访问的地址
    		String className = joinPoint.getSignature().getDeclaringTypeName();//访问的类名称
    		String methodName = joinPoint.getSignature().getName();//访问的方法名称
    		//打印重要信息到控制台
    		logger.info("URI : " + reqUri);  
    		logger.info("HTTP_METHOD : " + request.getMethod());  
    		logger.info("IP : " + remoteIp);  
    		logger.info("CLASS_NAME : " + className);  
    		logger.info("METHOD_NAME : " + methodName);  
    		//存储log信息
    		SysLog log = new SysLog(UUIDGenerator.getUUID(), className, methodName, remoteIp, reqUri);
    		logService.saveLog(log);
    	}
    }  
  
    //后置异常通知  
    @AfterThrowing("webLog()")  
    public void throwss(JoinPoint jp){  
        logger.info("方法异常时执行.....");  
    }  
  
    //后置最终通知,final增强，不管是抛出异常或者正常退出都会执行  
    @After("webLog()")  
    public void after(JoinPoint jp){  
        logger.info("方法停止执行...");  
    }  
  
    //环绕通知,环绕增强，相当于MethodInterceptor  
    @Around("webLog()")  
    public Object arround(ProceedingJoinPoint pjp) {  
        logger.info("方法开始执行...");  
        try {  
            Object o =  pjp.proceed();  
            logger.info("方法执行结束，结果是 :" + o);  
            return o;  
        } catch (Throwable e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
    
}  