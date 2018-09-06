package com.zhx.gmms.modules.sys.role.dao;

import java.util.List;
import java.util.Map;

import com.zhx.gmms.frame.CrudDao;
import com.zhx.gmms.modules.sys.right.bean.SysRight;
import com.zhx.gmms.modules.sys.role.bean.SysRole;
import com.zhx.gmms.modules.sys.user.bean.SysUser;

public interface SysRoleDao extends CrudDao<SysRole> {

	/**
	 * 查询每个角色信息以及该角色下的用户数
	 * @return
	 */
	List<Map<String, String>> roleUserCounts();

	/**
	 * 根据roleId查询该角色下的用户list信息
	 * @param roleId
	 * @return
	 */
	List<SysUser> selectUserByRole(String roleId);

	/**
	 * 根据roleId查询该角色下的用户list信息
	 * @param roleId
	 * @return
	 */
	List<SysRight> selectRightByRole(String roleId);

	/**
	 * 删除角色和权限关系
	 * @param roleId
	 * @return
	 */
	int deleteRoleRight(String roleId);

	/**
	 * 删除角色和用户关系
	 * @param roleId
	 * @return
	 */
	int deleteRoleUser(String roleId);

	/**
	 * 新增角色和权限关系
	 * @param map
	 * @return
	 */
	int insertRoleRights(Map<String, Object> map);

}
