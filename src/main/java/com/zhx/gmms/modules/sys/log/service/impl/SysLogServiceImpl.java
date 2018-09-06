package com.zhx.gmms.modules.sys.log.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhx.gmms.modules.sys.log.bean.SysLog;
import com.zhx.gmms.modules.sys.log.dao.SysLogDao;
import com.zhx.gmms.modules.sys.log.service.SysLogService;
import com.zhx.gmms.modules.sys.user.bean.SysUser;
import com.zhx.gmms.modules.sys.user.service.SysUserService;

@Service
@EnableTransactionManagement
public class SysLogServiceImpl implements SysLogService {

	@Autowired
	private SysLogDao logDao;
	
	@Autowired
	private SysUserService userService;
	
	/**
	 * 新增日志
	 */
	@Transactional(readOnly=false)
	public int saveLog(SysLog log) {
		return logDao.insert(log);
	}

	/**
	 * 分页查询日志信息
	 */
	@Transactional(readOnly=true)
	public Page<SysLog> findPageList(Map<String,String> wheres,int start, int limit, String dir) {
		PageHelper.startPage(start/10, limit,"create_time "+dir);
		return logDao.findPageList(wheres);
	}

	@Override
	public List<SysUser> findAllUser() {
		return userService.findList(new SysUser());
	}

}
