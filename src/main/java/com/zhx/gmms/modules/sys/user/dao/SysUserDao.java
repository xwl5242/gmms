package com.zhx.gmms.modules.sys.user.dao;

import java.util.List;
import java.util.Map;

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

	/**
	 * 获取用户所属角色，并查出所有角色，标注用户所拥有的角色
	 * @param userId
	 * @return
	 */
	List<Map<String, Object>> findUserRoles(String userId);

	/**
	 * 删除关联关系
	 * @param id
	 * @return
	 */
	int deleteUserRole(String id);

	/**
	 * 新增关联关系
	 * @param list
	 * @return
	 */
	int insertUserRoles(Map<String,Object> m);

	/**
	 * 查询用户角色关联的个数
	 * @param id
	 * @return
	 */
	int findUserRoleCount(String id);

//	int updateUserQuota(List<Map<String, Object>> lm);

}
