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
	List<Map<String, Object>> selectRightRoles(String rightId);

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

	/**
	 * 查询权限根据父id
	 * @param pid
	 * @return
	 */
	List<SysRight> findListByPid(String pid);

	/**
	 * 删除权限和角色的关联关系
	 * @param rightIds
	 * @return
	 */
	int deleteRightRole(List<String> rightIds);

}
