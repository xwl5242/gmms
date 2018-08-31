package com.zhx.gmms.modules.sys.user.dao;

import org.springframework.stereotype.Repository;

import com.zhx.gmms.frame.CrudDao;
import com.zhx.gmms.modules.sys.user.bean.SysUser;

/**
 * 用户dao
 * @author xwl
 */
@Repository
public interface SysUserDao extends CrudDao<SysUser> {

	/**
	 * 用户登录名称获取用户信息
	 * @param userCode
	 * @return
	 */
	SysUser selectByUserCode(String userCode);

//	int updateUserQuota(List<Map<String, Object>> lm);

}
