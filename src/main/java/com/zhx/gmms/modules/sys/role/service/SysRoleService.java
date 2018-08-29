package com.zhx.gmms.modules.sys.role.service;

import java.util.List;
import java.util.Map;

import com.zhx.gmms.modules.sys.role.bean.SysRole;

public interface SysRoleService {

	/**
	 * 新建角色
	 * @param role
	 * @return
	 */
	int saveRole(SysRole role)  throws Exception;

	/**
	 * 查询列表
	 * @param params
	 * @return
	 */
	Map<String, Object> queryRoleList(Map<String, String> params);

	/**
	 * 删除角色
	 * @param params
	 * @return
	 */
	boolean removeRoles(Map<String, String> params)throws Exception;

	/**
	 * 导出角色
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> exportRole(Map<String, String> params);

	/**
	 * 修改角色
	 * @param role
	 * @return
	 */
	int editRole(SysRole role)  throws Exception;

	/**
	 * 主键查询
	 * @param id
	 * @return
	 */
	SysRole queryById(String id);

	/**
	 * 根据角色名称查询角色信息
	 * @param roleName
	 * @return
	 */
	SysRole queryByRoleName(String roleName);

	/**
	 * 根据角色id查询角色权限关联
	 * @param id
	 * @return
	 */
	List<Map<String, Object>> queryRoleRightByRoleId(String id);

	/**
	 * 角色授权权限
	 * @param roleId
	 * @param rightIds
	 * @return
	 */
	boolean grantRoleRight(String roleId, String[] rightIds)throws Exception;

}
