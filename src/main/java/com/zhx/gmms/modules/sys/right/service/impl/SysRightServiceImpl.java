package com.zhx.gmms.modules.sys.right.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.zhx.gmms.modules.sys.right.bean.MenuData;
import com.zhx.gmms.modules.sys.right.bean.SysRight;
import com.zhx.gmms.modules.sys.right.dao.SysRightDao;
import com.zhx.gmms.modules.sys.right.service.SysRightService;
import com.zhx.gmms.modules.sys.role.bean.SysRole;
import com.zhx.gmms.modules.sys.role.service.SysRoleService;
import com.zhx.gmms.utils.UUIDGenerator;

@Service
@EnableTransactionManagement
public class SysRightServiceImpl implements SysRightService {

	@Autowired
	private SysRightDao rightDao;
	
	@Autowired
	private SysRoleService sysRoleService;
	
	/**
	 * 查询用户权限信息
	 */
	@Transactional(readOnly=true)
	public List<SysRight> queryRights(String userId) {
		return rightDao.selectRights(userId);
	}

	/**
	 * 获取所有权限信息
	 */
	@Transactional(readOnly=true)
	public List<SysRight> findList(SysRight sysRight) {
		return rightDao.findList(sysRight);
	}

	/**
	 * 获取权限角色
	 */
	@Transactional(readOnly=true)
	public List<Map<String, String>> findRightRoles(String rightId) {
		return rightDao.selectRightRoles(rightId);
	}

	/**
	 * 获取权限所属角色，并查出所有角色，标注权限所拥有的角色
	 */
	@Transactional(readOnly=true)
	public List<Map<String, Object>> findRole(String rightId) {
		List<Map<String, Object>> ret = new ArrayList<Map<String,Object>>();
		//当前权限所属的角色
		List<Map<String, String>> rrlist = findRightRoles(rightId);
		//查询所有角色信息
		List<SysRole> roleList = sysRoleService.findList(new SysRole());
		if(null!=roleList&&roleList.size()>0){
			for(SysRole role:roleList){
				//前端页面所需数据格式
				Map<String,Object> rrm = new HashMap<String, Object>();
				rrm.put("id", role.getId());
				rrm.put("text", role.getRoleName());
				//用来判断是否含有
				Map<String,String> rm = new HashMap<String, String>();
				rm.put("roleId", role.getId());
				rrm.put("permission", rrlist.contains(rm));
				ret.add(rrm);
			}
		}
		return ret;
	}

	/**
	 * 删除权限
	 */
	@Transactional(readOnly=false)
	public int removeRight(String id) {
		return rightDao.delete(id);
	}

	/**
	 * 保存权限，前端页面新建菜单的形式。以MenuData的类型
	 */
	@Transactional(readOnly=false)
	public int saveRightByMenuData(MenuData md) {
		int result = 0;
		//保存权限
		SysRight right = new SysRight();
		String rightId = UUIDGenerator.getUUID();
		right.setId(rightId);
		right.setRightName(md.getText());
		right.setRightDesc(md.getText());
		right.setPid(md.getPid());
		right.setRightUrl(md.getUrl());
		right.setIcon(md.getIcon());
		int maxSeq = rightDao.selectMaxSeq(right.getPid())+5;
		right.setSeq(maxSeq);
		result += rightDao.insert(right);
		//保存权限和角色关系
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		List<MenuData.Auth> auths = md.getAuth();
		if(null!=auths&&auths.size()>0){
			for(MenuData.Auth auth:auths){
				Map<String,String> m = new HashMap<String, String>();
				m.put("id", UUIDGenerator.getUUID());
				m.put("rightId", rightId);
				m.put("roleId", auth.getId());
				list.add(m);
			}
		}
		result += rightDao.insertRightRoles(list);
		return result;
	}

}
