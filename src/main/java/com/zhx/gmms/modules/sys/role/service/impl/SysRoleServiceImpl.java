package com.zhx.gmms.modules.sys.role.service.impl;

import java.util.Arrays;
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

	@Transactional(readOnly=true)
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
	@Transactional(rollbackFor=Exception.class)
	public String saveRole(String roleId,String roleName, String[] roleAuth) throws Exception{
		String id = "";
		try{
			int deal=0,count=0;
			boolean isUpdate = StringUtils.isNotBlank(roleId);
			SysRole role = new SysRole();
			//角色id，如果角色id为空或null为新增，如果角色id存在值为更新
			role.setRoleName(roleName);
			role.setRoleDesc(roleName);
			//roleId不为null和空 即为更新操作
			if(StringUtils.isNotBlank(roleId)){
				//更新
				role.setId(roleId);
				deal += roleDao.update(role);
			}else{
				//新增
				role.setId(UUIDGenerator.getUUID());
				deal += roleDao.insert(role);
			}
			//新增角色
			if(null!=roleAuth&&roleAuth.length>0){
				//更新角色信息，需要更新角色的权限信息，先删除之前的关联关系，在添加新的关系
				if(StringUtils.isNotBlank(roleId)){
					count+=roleDao.roleUserCounts().size();
					deal +=roleDao.deleteRoleRight(roleId);
				}
				//添加新的关系
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("roleId", role.getId());
				map.put("list", Arrays.asList(roleAuth));
				deal += roleDao.insertRoleRights(map);
			}
			boolean flag = isUpdate?(deal==count+roleAuth.length+1):(deal==roleAuth.length+1);
			if(!flag) throw new Exception("角色信息操作异常！");
			id=role.getId();
		}catch(Exception e){
			id=null;throw e;
		}
		return id;
	}

	/**
	 * 删除角色
	 */
	@Transactional(rollbackFor=Exception.class)
	public boolean deleteRole(String roleId) throws Exception{
		boolean ret = false;
		try{
			int deal=0,count=0;
			//删除角色
			deal += roleDao.delete(Arrays.asList(roleId));
			//删除角色和权限的关联
			deal += roleDao.deleteRoleRight(roleId);
			//删除角色和用户的关联
			deal += roleDao.deleteRoleUser(roleId);
			//查询该角色关联的用户和权限的个数
			count+=roleDao.selectUserByRole(roleId).size();
			count+=roleDao.selectRightByRole(roleId).size();
			ret = deal+1==count;
			if(count!=deal+1) throw new Exception("删除角色信息异常！");
		}catch(Exception e){
			ret = false;throw e;
		}
		return ret;
	}

}
