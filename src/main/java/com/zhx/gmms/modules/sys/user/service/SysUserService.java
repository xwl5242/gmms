package com.zhx.gmms.modules.sys.user.service;

import java.util.List;

import com.zhx.gmms.modules.sys.right.bean.SysRight;
import com.zhx.gmms.modules.sys.theme.bean.SysTheme;
import com.zhx.gmms.modules.sys.user.bean.SysUser;

public interface SysUserService {

	/**
	 * 主键获取用户信息
	 * @param id 主键
	 * @return
	 */
	SysUser get(String id);

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
	 * 更新用户信息
	 * @param loginUser
	 * @return
	 */
	int editUser(SysUser user);

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
	SysTheme updateTheme(SysTheme sysTheme,String userId);

}
