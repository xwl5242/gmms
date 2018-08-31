package com.zhx.gmms.modules.sys.right.dao;

import java.util.List;
import java.util.Map;

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
	 * 获取权限角色
	 * @param rightId
	 * @return
	 */
	List<Map<String, String>> selectRightRoles(String rightId);

	/**
	 * 批量新增权限角色
	 * @param list
	 * @return
	 */
	int insertRightRoles(List<Map<String, String>> list);

	/**
	 * 某一菜单所有子菜单的最大排序值
	 * @param pid
	 * @return
	 */
	int selectMaxSeq(String pid);

}
