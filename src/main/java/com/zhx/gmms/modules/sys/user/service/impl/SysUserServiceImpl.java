package com.zhx.gmms.modules.sys.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.zhx.gmms.modules.sys.right.bean.SysRight;
import com.zhx.gmms.modules.sys.right.service.SysRightService;
import com.zhx.gmms.modules.sys.theme.bean.SysTheme;
import com.zhx.gmms.modules.sys.theme.service.SysThemeService;
import com.zhx.gmms.modules.sys.user.bean.SysUser;
import com.zhx.gmms.modules.sys.user.dao.SysUserDao;
import com.zhx.gmms.modules.sys.user.service.SysUserService;
import com.zhx.gmms.utils.UUIDGenerator;

@Service
@EnableTransactionManagement
public class SysUserServiceImpl implements SysUserService {
	
	@Autowired
	private SysUserDao sysUserDao;
	
	@Autowired
	private SysRightService sysRightService;
	
	@Autowired
	private SysThemeService sysThemeService;

	/**
	 * 主键获取用户信息
	 */
	@Transactional(readOnly=true)
	public SysUser get(String id) {
		return sysUserDao.get(id);
	}

	/**
	 * 获取用户list
	 */
	@Transactional(readOnly=true)
	public List<SysUser> findList(SysUser user) {
		return sysUserDao.findList(user);
	}

	/**
	 * 登录名称获取用户信息
	 */
	@Transactional(readOnly=true)
	public SysUser queryByUserCode(String userCode) {
		return sysUserDao.selectByUserCode(userCode);
	}

	/**
	 * 更新用户信息
	 */
	@Transactional(readOnly=false)
	public int editUser(SysUser user) {
		return sysUserDao.update(user);
	}

	/**
	 * 获取用户的主题信息
	 */
	@Transactional(readOnly=true)
	public SysTheme getCurUserTheme(String themeId) {
		return sysThemeService.get(themeId);
	}

	/**
	 * 获取用户权限信息
	 */
	@Transactional(readOnly=true)
	public List<SysRight> queryRights(String id) {
		return sysRightService.queryRights(id);
	}

	/**
	 * 更新用户的主题信息
	 */
	@Transactional(readOnly=false)
	public SysTheme updateTheme(SysTheme sysTheme,String userId) {
		int ret = 0;
		//业务逻辑：如果用户的主题id为-1，则证明用户第一次自定义主题信息，此时需要新增主题信息，并更新用户主题的关联关系。否则，直接更新
		String themeId = get(userId).getThemeId();
		if("-1".equals(themeId)){
			//第一次自定义主题信息，新增并修改关联关系
			sysTheme.setId(UUIDGenerator.getUUID());
			ret += sysThemeService.saveSysTheme(sysTheme);
			SysUser user = new SysUser();
			user.setId(userId);
			user.setThemeId(sysTheme.getId());
			ret += editUser(user);
		}else{
			//已存在主题信息此时更新主题信息
			sysTheme.setId(themeId);
			ret += sysThemeService.editSysTheme(sysTheme);
		}
		return ret>=1?sysTheme:null;
	}
	
}
