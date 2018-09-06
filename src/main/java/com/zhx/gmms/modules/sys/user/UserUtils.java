package com.zhx.gmms.modules.sys.user;

import java.util.HashMap;
import java.util.Map;

import com.zhx.gmms.frame.Const;
import com.zhx.gmms.modules.sys.user.bean.SysUser;

public class UserUtils {

	public static Map<String,Object> sessionMap = new HashMap<String, Object>();
	
	/**
	 * 获取当前用户
	 * @return
	 */
	public static SysUser getCurUser(){
		return (SysUser) sessionMap.get(Const.SESSION_USER);
	}
	
	/**
	 * 获取当前用户的id
	 * @return
	 */
	public static String getCurUserId(){
		Object o = sessionMap.get(Const.SESSION_USER_ID);
		return o==null?"":o.toString();
	}
	
	/**
	 * 获取当前用户的userCode
	 * @return
	 */
	public static String getCurUserCode(){
		return null==getCurUser()?"":getCurUser().getUserCode();
	}
}
