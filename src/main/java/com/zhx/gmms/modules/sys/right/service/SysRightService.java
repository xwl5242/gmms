package com.zhx.gmms.modules.sys.right.service;

import java.util.List;

import com.zhx.gmms.modules.sys.right.bean.SysRight;

public interface SysRightService {

	/**
	 * 查询用户权限信息
	 * @param id
	 * @return
	 */
	List<SysRight> queryRights(String id);

	/**
	 * 
	 * @return
	 */
	List<SysRight> findList();

}
