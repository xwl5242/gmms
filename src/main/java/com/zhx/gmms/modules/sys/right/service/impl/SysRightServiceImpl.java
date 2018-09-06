package com.zhx.gmms.modules.sys.right.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
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
	@Transactional(readOnly=true)
	public List<SysRight> findListByPid(String pid){
		return rightDao.findListByPid(pid);
	}
	
	/**
	 * 获取权限所属角色，并查出所有角色，标注权限所拥有的角色
	 */
	@Transactional(readOnly=true)
	public List<Map<String, Object>> findRightRoles(String rightId) {
		return rightDao.selectRightRoles(rightId);
	}

	/**
	 * 删除权限
	 */
	@Transactional(rollbackFor=Exception.class)
	public boolean removeRight(String id)  throws Exception{
		int i = 0;
		//递归拿到该菜单的子菜单，子子菜单，子子子菜单...
		List<String> rightIds = recRights(id);
		rightIds.add(id);
		//删除本菜单以及子菜单的所有  菜单和权限关系
		i+=rightDao.delete(rightIds);
		i+=rightDao.deleteRightRole(rightIds);
		return i>=rightIds.size()+1;
	}
	
	/**
	 * 保存权限，前端页面新建菜单的形式。以MenuData的类型
	 */
	@Transactional(rollbackFor=Exception.class)
	public boolean saveTopRightByMenuData(MenuData md)  throws Exception{
		boolean ret = false;
		try{
			int deal = 0,count = 0;
			boolean isUpdate = "update".equals(md.getType());
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
				right.setId(UUIDGenerator.getUUID());
				right.setSeq(maxSeq+5);
				deal += rightDao.insert(right);
			}else{
				//修改
				right.setId(md.getId());
				deal += rightDao.update(right);
			}
			//查询权限和角色关系
			List<String> list = new ArrayList<String>();
			List<MenuData.Auth> auths = md.getAuth();
			if(null!=auths&&auths.size()>0){
				for(MenuData.Auth auth:auths){
					list.add(auth.getId());
				}
				if("update".equals(md.getType())){
					//如果是修改，权限角色关系可能也被修改，先删除之前的
					count+=rightDao.selectRightRoles(right.getId()).size();
					deal+=rightDao.deleteRightRole(Arrays.asList(right.getId()));
				}
				Map<String,Object> m = new HashMap<String, Object>();
				m.put("rightId", right.getId());
				m.put("list",list);
				deal += rightDao.insertRightRoles(m);
			}
			ret = isUpdate?(deal==count+auths.size()+1):(deal==auths.size()+1);
			if(!ret) throw new Exception("操作权限信息异常！");
		}catch(Exception e){
			ret = false;throw e;
		}
		return ret;
	}

	/**
	 * 保存子权限，前端页面新建菜单的形式。以MenuData的类型
	 */
	@Transactional(rollbackFor=Exception.class)
	public boolean saveSubRightByMenuData(MenuData md)  throws Exception{
		boolean r = true;
		try{
			List<MenuData> mds = recMenuData(md);
			if(null!=mds&&mds.size()>0){
				for(MenuData m:mds){
					r=r&&saveTopRightByMenuData(m);
				}
			}
			if(!r) throw new Exception("保存子权限操作异常！");
		}catch(Exception e){
			r=false;throw e;
		}
		return r;
	}

	/**
	 * 更新顶部菜单顺序
	 */
	@Transactional(rollbackFor=Exception.class)
	public boolean updateTopOrder(List<Map<String,Object>> list) throws Exception{
		int r = 0;
		for(Map<String,Object> tm:list){
			String rightId = tm.get("id").toString();
			int seq = Integer.valueOf(tm.get("orderNo").toString())-1;
			SysRight right = new SysRight();
			right.setId(rightId);
			right.setSeq(seq*5);
			r+=rightDao.update(right);
		}
		return r==list.size();
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

}
