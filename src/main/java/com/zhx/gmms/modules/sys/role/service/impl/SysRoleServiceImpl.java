package com.zhx.gmms.modules.sys.role.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.zhx.gmms.modules.sys.right.bean.SysRight;
import com.zhx.gmms.modules.sys.right.dao.SysRightDao;
import com.zhx.gmms.modules.sys.role.bean.SysRole;
import com.zhx.gmms.modules.sys.role.dao.SysRoleDao;
import com.zhx.gmms.modules.sys.role.service.SysRoleService;
import com.zhx.gmms.modules.sys.user.bean.SysUser;
import com.zhx.gmms.utils.UUIDGenerator;

@Service
@EnableTransactionManagement
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
	private SysRoleDao roleDao;
	
	@Autowired
	private SysRightDao rightDao;

	@Override
	public List<SysRole> findList(SysRole sysRole) {
		return roleDao.findList(sysRole);
	}
	
	/**
	 * 查询每个角色信息以及该角色下的用户数
	 */
	@Transactional(readOnly=true)
	public List<Map<String, String>> roleUserCounts() {
		return roleDao.roleUserCounts();
	}

	/**
	 * 根据roleId查询该角色下的用户list信息
	 */
	@Transactional(readOnly=true)
	public List<SysUser> findUserByRole(String roleId) {
		return roleDao.selectUserByRole(roleId);
	}

	/**
	 * 根据roleId查询该角色下的权限list信息
	 */
	@Transactional(readOnly=true)
	public List<SysRight> findRightByRole(String roleId) {
		return roleDao.selectRightByRole(roleId);
	}

	/**
	 * 新增角色
	 */
	@Override
	public String saveRole(String roleId,String roleName, String[] roleAuth) {
		//角色id，如果角色id为空或null为新增，如果角色id存在值为更新
		String id = StringUtils.isNotBlank(roleId)?roleId:UUIDGenerator.getUUID();
		SysRole role = new SysRole();
		role.setId(id);
		role.setRoleName(roleName);
		role.setRoleDesc(roleName);
		//roleId不为null和空 即为更新操作
		int ret = 0;
		if(StringUtils.isNotBlank(roleId)){
			//更新
			ret += roleDao.update(role);
		}else{
			//新增
			ret += roleDao.insert(role);
		}
		//新增角色
		if(null!=roleAuth&&roleAuth.length>0){
			//遍历角色拥有的权限，并批量新增
			List<Map<String,String>> list = new ArrayList<Map<String,String>>();
			for(String ra:roleAuth){
				Map<String,String> map = new HashMap<String, String>();
				map.put("id", UUIDGenerator.getUUID());
				map.put("roleId", role.getId());
				map.put("rightId", ra);
				list.add(map);
			}
			//更新角色信息，需要更新角色的权限信息，先删除之前的关联关系，在添加新的关系
			if(StringUtils.isNotBlank(roleId)){
				ret +=roleDao.deleteRoleRight(roleId);
			}
			//添加新的关系
			ret += rightDao.insertRightRoles(list);
		}
		return ret==roleAuth.length+1?id:null;
	}

	/**
	 * 删除角色
	 */
	@Override
	public int deleteRole(String roleId) {
		List<String> roleIds = new ArrayList<String>();
		roleIds.add(roleId);
		int r = roleDao.delete(roleIds);
		r+=roleDao.deleteRoleRight(roleId);
		r+=roleDao.deleteRoleUser(roleId);
		return r;
	}

}
