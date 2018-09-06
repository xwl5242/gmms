package com.zhx.gmms.modules.sys.log.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.zhx.gmms.frame.CrudDao;
import com.zhx.gmms.modules.sys.log.bean.SysLog;

@Repository
public interface SysLogDao extends CrudDao<SysLog> {

	/**
	 * 
	 * @return
	 */
	Page<SysLog> findPageList(Map<String,String> wheres);

}
