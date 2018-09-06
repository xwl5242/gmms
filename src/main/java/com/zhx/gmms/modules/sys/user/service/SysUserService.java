package com.zhx.gmms.modules.sys.user.service;

import java.util.List;
import java.util.Map;

import com.zhx.gmms.modules.sys.right.bean.SysRight;
import com.zhx.gmms.modules.sys.theme.bean.SysTheme;
import com.zhx.gmms.modules.sys.user.bean.SysUser;

public interface SysUserService {

	/**
	 * 获取用户列表
	 * @param user 用户信息
	 * @return
	 */
	List<SysUser> findList(SysUser user);

	/**
	 * 登录名称获取用户信息
	 * @param userCode
	 * @return
	 */
	SysUser queryByUserCode(String userCode);

	/**
	 * 获取用户的主题信息
	 * @param themeId
	 * @return
	 */
	SysTheme getCurUserTheme(String themeId);

	/**
	 * 获取当前用户的权限信息
	 * @param id
	 * @return
	 */
	List<SysRight> queryRights(String id);

	/**
	 * 用户更新主题信息
	 * @param sysTheme
	 * @return
	 */
	SysTheme updateTheme(SysTheme sysTheme,String userId) throws Exception;

	/**
	 * 获取用户所属角色，并查出所有角色，标注用户所拥有的角色
	 * @param userId
	 * @return
	 */
	List<Map<String, Object>> findUserRoles(String userId);

	/**
	 * 新增或更新用户
	 * @param user
	 * @param roleId
	 * @return
	 */
	boolean saveOrUpdateUser(SysUser user, String[] roleId) throws Exception;

	/**
	 * 更新用户信息
	 * @param editUser
	 * @return
	 */
	int editUser(SysUser editUser) throws Exception;

	/**
	 * 删除用户
	 * @param userId
	 * @return
	 */
	int removeUser(String userId) throws Exception;

//	void initQuota();

}
