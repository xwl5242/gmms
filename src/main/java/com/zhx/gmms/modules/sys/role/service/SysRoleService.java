package com.zhx.gmms.modules.sys.role.service;

import java.util.List;
import java.util.Map;

import com.zhx.gmms.modules.sys.right.bean.SysRight;
import com.zhx.gmms.modules.sys.role.bean.SysRole;
import com.zhx.gmms.modules.sys.user.bean.SysUser;

public interface SysRoleService {
	
	/**
	 * 
	 * @param sysRole
	 * @return
	 */
	public List<SysRole> findList(SysRole sysRole);

	/**
	 * 查询每个角色信息以及该角色下的用户数
	 * @return
	 */
	public List<Map<String, String>> roleUserCounts();

	/**
	 * 根据roleId查询该角色下的用户list信息
	 * @param roleId
	 * @return
	 */
	public List<SysUser> findUserByRole(String roleId);

	/**
	 * 根据roleId查询该角色下的权限list信息
	 * @param roleId
	 * @return
	 */
	public List<SysRight> findRightByRole(String roleId);

	/**
	 * 新增角色
	 * @param roleName
	 * @param roleAuth
	 * @return
	 */
	public String saveRole(String roleId,String roleName, String[] roleAuth);

	/**
	 * 删除角色
	 * @param roleId
	 * @return
	 */
	public int deleteRole(String roleId);

}
