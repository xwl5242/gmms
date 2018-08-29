package com.zhx.gmms.modules.sys.role.dao;

import java.util.List;
import java.util.Map;

import com.zhx.gmms.frame.CrudDao;
import com.zhx.gmms.modules.sys.role.bean.SysRole;

public interface SysRoleDao extends CrudDao<SysRole> {


	/**
	 * 分页查询角色列表
	 * @param params
	 * @return
	 */
	Map<String, Object> selectRoleList(Map<String, String> params);

	/**
	 * 导出角色
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> selectRoles4Export(Map<String, String> params);

	/**
	 * 根据角色id查询角色权限关联
	 * @param id
	 * @return
	 */
	List<Map<String, Object>> selectRoleRightByRoleId(String id);

	/**
	 * 角色授权权限
	 * @param roleId
	 * @param rightIds
	 * @return
	 */
	int updateRoleRight(String roleId, String[] rightIds);

	/**
	 * 根据角色id查询角色和用户关联个数
	 * @param ids
	 * @return
	 */
	int selectUserRoleCountByRoleId(String ids);

	/**
	 * 根据角色id查询角色和权限关联个数
	 * @param ids
	 * @return
	 */
	int selectRoleRightCountByRoleId(String ids);

}
