package com.zhx.gmms.modules.sys.log.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.zhx.gmms.modules.sys.log.bean.SysLog;
import com.zhx.gmms.modules.sys.user.bean.SysUser;

public interface SysLogService {

	/**
	 * 新增日志
	 * @param log
	 * @return
	 */
	int saveLog(SysLog log);

	/**
	 * 查询所有用户信息
	 * @return
	 */
	List<SysUser> findAllUser();
	
	/**
	 * 分页查询
	 * @param start
	 * @param limit
	 * @param dir
	 * @return
	 */
	Page<SysLog> findPageList(Map<String,String> wheres,int start, int limit, String dir);

	/**
	 * 查询用户日志数
	 * @param userId
	 * @return
	 */
	int findCount(String userId);
}
