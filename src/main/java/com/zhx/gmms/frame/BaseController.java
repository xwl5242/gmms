package com.zhx.gmms.frame;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * baseController
 * @author xwl
 */
public class BaseController {
	
	private Logger logger = LoggerFactory.getLogger(BaseController.class);

	@Autowired
	public HttpServletRequest request;
	
	@Autowired
	public Environment env;
	
	public ObjectMapper objectMapper = new ObjectMapper();
	
	/**
	 * 获取pagelist信息
	 * @param list 要处理的list
	 * @return
	 */
//	public <T> PageInfo<T> pageList(List<T> list){
//		int pageNo = Integer.valueOf(request.getParameter("pageNo"));
//		int pageSize = Integer.valueOf(request.getParameter("pageSize"));
//		PageHelper.startPage(pageNo, pageSize);
//		return new PageInfo<T>(list);
//	}
	
	/**
	 * 清空session中的所有信息
	 * @param request
	 */
	public void clearSession(HttpServletRequest request){
		HttpSession session = request.getSession();
		Enumeration<String> ans = session.getAttributeNames();
		while(ans.hasMoreElements()){
			String key = ans.nextElement();
			session.removeAttribute(key);
		}
	}
	
	/**
	 * 返回json字符串
	 * @param o
	 * @return
	 */
	public String toJson(Object o){
		String result=null;
		try{
			result = objectMapper.writeValueAsString(o);
		}catch(Exception e){
			logger.error("returnJsonStr error:"+e.getMessage());
		}
		return result;
	}
	
	/**
	 * 返回json
	 * @param isSuccess
	 * @return
	 */
	public String toJson(boolean isSuccess){
		return toJson(isSuccess, isSuccess?"操作成功！":"操作失败！");
	}
	
	/**
	 * 返回json
	 * @return
	 */
	public String toJson(boolean isSuccess,String msg){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("success", isSuccess);
		map.put("msg",msg);
		return toJson(map);
	}
	
	/**
	 * 
	 * @param isSuccess
	 * @return
	 */
	public String toJson(boolean isSuccess,Map<String,Object> customMap){
		return toJson(isSuccess,isSuccess?"操作成功！":"操作失败！",customMap);
	}
	
	/**
	 * 返回json，可拼接自己的key,value信息
	 * @param customMap
	 * @return
	 */
	public String toJson(boolean isSuccess,String msg,Map<String,Object> customMap){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("success", isSuccess);
		map.put("msg",msg);
		map.putAll(customMap);
		return toJson(map);
	}
	
	/**
	 * 返回dataTable所需的字符串信息
	 * @param list
	 * @return
	 */
	public String returnDataTables(List<?> list){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("data", list);
		return toJson(map);
	}
}
