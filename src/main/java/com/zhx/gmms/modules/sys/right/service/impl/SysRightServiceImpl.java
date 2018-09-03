package com.zhx.gmms.modules.sys.right.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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
	 * 查询权限根据父id
	 * @param pid
	 * @return
	 */
	public List<SysRight> findListByPid(String pid){
		return rightDao.findListByPid(pid);
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
		int i = 0;
		//递归拿到该菜单的子菜单，子子菜单，子子子菜单...
		List<String> rightIds = recRights(id);
		rightIds.add(id);
		//删除本菜单以及子菜单的所有  菜单和权限关系
		i+=rightDao.delete(rightIds);
		i+=rightDao.deleteRightRole(rightIds);
		return i;
	}
	
	/**
	 * 保存权限，前端页面新建菜单的形式。以MenuData的类型
	 */
	@Transactional(readOnly=false)
	public int saveTopRightByMenuData(MenuData md) {
		int result = 0;
		//配置权限信息
		SysRight right = new SysRight();
		right.setRightName(md.getText());
		right.setRightDesc(md.getText());
		right.setPid(md.getPid());
		right.setRightUrl(md.getUrl());
		right.setIcon(md.getIcon());
		if("add".equals(md.getType())){
			//新增
			int maxSeq = rightDao.selectMaxSeq(right.getPid());//查询当前权限节点下子节点的最大排序数值
			String rightId = StringUtils.isBlank(md.getId())?UUIDGenerator.getUUID():md.getId();//生成uuid，为新增做准备
			right.setId(rightId);
			right.setSeq(maxSeq+5);
			result += rightDao.insert(right);
		}else{
			//修改
			right.setId(md.getId());
			result += rightDao.update(right);
		}
		//查询权限和角色关系
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		List<MenuData.Auth> auths = md.getAuth();
		if(null!=auths&&auths.size()>0){
			for(MenuData.Auth auth:auths){
				Map<String,String> m = new HashMap<String, String>();
				m.put("id", UUIDGenerator.getUUID());
				m.put("rightId", right.getId());
				m.put("roleId", auth.getId());
				list.add(m);
			}
			if("update".equals(md.getType())){
				//如果是修改，权限角色关系可能也被修改，先删除之前的
				List<String> rightIds = new ArrayList<String>();
				rightIds.add(right.getId());
				rightDao.deleteRightRole(rightIds);
			}
			result += rightDao.insertRightRoles(list);
		}
		return result;
	}

	/**
	 * 保存子权限，前端页面新建菜单的形式。以MenuData的类型
	 */
	@Override
	public int saveSubRightByMenuData(MenuData md) {
		int r = 0;
		List<MenuData> mds = recMenuData(md);
		if(null!=mds&&mds.size()>0){
			for(MenuData m:mds){
				r += saveTopRightByMenuData(m);
			}
		}
		return r;
	}

	/**
	 * 递归查询某一权限的所有子权限id
	 * @param pid
	 * @return
	 */
	private List<String> recRights(String pid){
		List<String> rightIds = new ArrayList<String>();
		//所有子菜单
		List<SysRight> rlist = findListByPid(pid);//所有子菜单
		if(null!=rlist&&rlist.size()>0){
			for(SysRight r:rlist){
				rightIds.add(r.getId());
				rightIds.addAll(recRights(r.getId()));
			}
		}
		return rightIds;
	}
	
	/**
	 * 递归遍历MenuData找到其中需要新增或者更新的数据
	 * @param md
	 * @return
	 */
	private List<MenuData> recMenuData(MenuData md){
		List<MenuData> mds = new ArrayList<MenuData>();
		if(null!=md.getChildren()&&md.getChildren().size()>0){
			for(MenuData m : md.getChildren()){
				if(StringUtils.isNotBlank(m.getType())){
					mds.add(m);
				}
				mds.addAll(recMenuData(m));
			}
		}
		return mds;
	}

	/**
	 * 更新顶部菜单顺序
	 */
	@Override
	public int updateTopOrder(List<Map> list) throws Exception{
		int r = 0;
		for(Map tm:list){
			String rightId = tm.get("id").toString();
			int seq = Integer.valueOf(tm.get("orderNo").toString())-1;
			SysRight right = new SysRight();
			right.setId(rightId);
			right.setSeq(seq*5);
			r+=rightDao.update(right);
		}
		return r;
	}
}
