package com.zhx.gmms.modules.sys.user.service.impl;

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
import com.zhx.gmms.modules.sys.right.service.SysRightService;
import com.zhx.gmms.modules.sys.theme.bean.SysTheme;
import com.zhx.gmms.modules.sys.theme.service.SysThemeService;
import com.zhx.gmms.modules.sys.user.UserUtils;
import com.zhx.gmms.modules.sys.user.bean.SysUser;
import com.zhx.gmms.modules.sys.user.dao.SysUserDao;
import com.zhx.gmms.modules.sys.user.service.SysUserService;
import com.zhx.gmms.utils.DESUtils;
import com.zhx.gmms.utils.DateUtils;
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
	 * 修改用户信息
	 */
	@Transactional(rollbackFor=Exception.class)
	public int editUser(SysUser editUser) throws Exception{
		int editRet = sysUserDao.update(editUser);
		if(editRet==0) throw new Exception("修改用户信息失败！");
		return editRet;
	}
	
	/**
	 * 更新用户的主题信息
	 */
	@Transactional(rollbackFor=Exception.class)
	public SysTheme updateTheme(SysTheme sysTheme,String userId) throws Exception{
		int ret = 0;
		try{
			//业务逻辑：如果用户的主题id为-1，则证明用户第一次自定义主题信息，此时需要新增主题信息，并更新用户主题的关联关系。否则，直接更新
			String themeId = UserUtils.getCurUser().getThemeId();
			if("-1".equals(themeId)){
				//第一次自定义主题信息，新增并修改关联关系
				sysTheme.setId(UUIDGenerator.getUUID());
				ret += sysThemeService.saveSysTheme(sysTheme);
				SysUser user = new SysUser();
				user.setId(userId);
				user.setThemeId(sysTheme.getId());
				ret += sysUserDao.update(user);
				if(ret!=2) throw new Exception("用户更新主题失败！");
			}else{
				//已存在主题信息此时更新主题信息
				sysTheme.setId(themeId);
				ret += sysThemeService.editSysTheme(sysTheme);
				if(ret!=1) throw new Exception("用户更新主题失败！");
			}
		}catch(Exception e){
			ret = 0;
			throw e;
		}
		return ret>=1?sysTheme:null;
	}

	/**
	 * 获取用户所属角色，并查出所有角色，标注用户所拥有的角色
	 */
	@Transactional(readOnly=false)
	public List<Map<String, Object>> findUserRoles(String userId) {
		return sysUserDao.findUserRoles(userId);
	}

	/**
	 * 新增或更新用户信息
	 */
	@Transactional(rollbackFor=Exception.class)
	public boolean saveOrUpdateUser(SysUser user, String[] roleIds) throws Exception{
		int deal=0,urcount=0;boolean result=false;
		//如果用户的id不为空或null即为更新操作，否则新增操作
		boolean isUpdate = StringUtils.isNotBlank(user.getId());
		try{
			//启用禁用状态
			if(StringUtils.isBlank(user.getUseStatus())){
				user.setUseStatus("NORMAL");
			}
			//处理用户的密码信息
			if(StringUtils.isNotBlank(user.getPassword())){
				//加密处理
				String pw = DESUtils.encrypt(user.getPassword());
				user.setPassword(pw);
			}
			//如果用户的id不为空或null即为更新操作，否则新增操作
			if(isUpdate){
				//更新
				user.setUpdator(UserUtils.getCurUserId());
				user.setUpdateTime(DateUtils.now2yyyyMMddHHmmssStr());
				deal+=sysUserDao.update(user);
			}else{
				//新增
				user.setId(UUIDGenerator.getUUID());
				user.setCreator(UserUtils.getCurUserId());
				user.setCreateTime(DateUtils.now2yyyyMMddHHmmssStr());
				deal+=sysUserDao.insert(user);
			}
			//新增或更新用户和角色关系
			if(null!=roleIds&&roleIds.length>0){
				//更新操作需要先删除之前的关系，在新增新的关系
				if(isUpdate){
					urcount+=sysUserDao.findUserRoleCount(user.getId());
					//删除之前的关系
					deal+=sysUserDao.deleteUserRole(user.getId());
				}
				//新增用户和角色关系
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("userId", user.getId());
				map.put("list", Arrays.asList(roleIds));
				deal+=sysUserDao.insertUserRoles(map);
			}
			//根据操作类型，来判断数据库操作是否成功
			result = isUpdate?(deal==roleIds.length+urcount+1):(deal==roleIds.length+1);
			if(!result) throw new Exception("数据库操作失败！");
		}catch(Exception e){
			result = false;
			throw e;
		}
		return result;
	}

	/**
	 * 删除用户
	 */
	@Transactional(rollbackFor=Exception.class)
	public int removeUser(String userId) {
		return sysUserDao.delete(Arrays.asList(userId));
	}

//	@Override
//	public void initQuota() {
//		try {
//			List<Map<String,Object>> lm = new ArrayList<Map<String,Object>>();
//			File file = new File("f:\\2.xlsx");
//			FileInputStream fin = new FileInputStream(file);
//			List<List<Object>> list = CommonExcelImport.getBankListByExcel(fin,file.getName());
//			for(List<Object> li:list){
//				String id = li.get(0).toString();//会员身份证
//				int q = Integer.valueOf(li.get(1)+"");
//				id = id.replaceAll(" ","");
//				Map<String,Object> m = new HashMap<String, Object>();
//				m.put("id", "'"+id+"'");
//				m.put("q", q);
//				lm.add(m);
//			}
//			int r = sysUserDao.updateUserQuota(lm);
//			System.out.println("excel总条数："+list.size()+"；更新成功总条数："+r);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
}
