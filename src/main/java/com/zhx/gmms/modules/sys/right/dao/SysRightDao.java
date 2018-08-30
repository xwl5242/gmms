package com.zhx.gmms.modules.sys.right.dao;

import java.util.List;

import com.zhx.gmms.frame.CrudDao;
import com.zhx.gmms.modules.sys.right.bean.SysRight;

public interface SysRightDao extends CrudDao<SysRight>{

	/**
	 * 查询用户权限信息
	 * @param userId
	 * @return
	 */
	List<SysRight> selectRights(String userId);

	/**
	 * 获取所有权限信息
	 * @return
	 */
	List<SysRight> selectList();

}
