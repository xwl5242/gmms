package com.zhx.gmms.modules.sys.right.service;

import java.util.List;
import java.util.Map;

import com.zhx.gmms.modules.sys.right.bean.SysRight;

public interface SysRightService {

	/**
	 * 新建权限
	 * @param right
	 * @return
	 */
	int saveRight(SysRight right)  throws Exception;

	/**
	 * 根据权限登录名查询权限信息
	 * @param rightCode
	 * @return
	 */
	SysRight queryByRightName(String rightName);

	/**
	 * 查询列表
	 * @param params
	 * @return
	 */
	Map<String, Object> queryRightList(Map<String, String> params);

	/**
	 * 删除权限
	 * @param params
	 * @return
	 */
	boolean removeRights(Map<String, String> params) throws Exception;

	/**
	 * 修改权限
	 * @param right
	 * @return
	 */
	int editRight(SysRight right)  throws Exception;

	/**
	 * 主键查询
	 * @param id
	 * @return
	 */
	SysRight queryById(String id);

	/**
	 * 查询用户权限信息
	 * @param id
	 * @return
	 */
	List<SysRight> queryRights(String id);

}
