package com.zhx.gmms.modules.sys.right.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.zhx.gmms.frame.Const;
import com.zhx.gmms.modules.sys.right.bean.SysRight;
import com.zhx.gmms.modules.sys.right.dao.SysRightDao;
import com.zhx.gmms.modules.sys.right.service.SysRightService;
import com.zhx.gmms.utils.DateUtils;
import com.zhx.gmms.utils.UUIDGenerator;

@Service
@EnableTransactionManagement
public class SysRightServiceImpl implements SysRightService {

	@Autowired
	private SysRightDao rightDao;
	
	/**
	 * 新建权限
	 */
	@Transactional(readOnly=false)
	public int saveRight(SysRight right) throws Exception{
		int insertRet = 0;
		try {
			right.setId(UUIDGenerator.getUUID());
			right.setIsDel("0");
			right.setIsLeaf("1");
			right.setIcon(Const.RIGHT_CION_HTML);
			right.setCreateTime(DateUtils.date2yyyyMMddHHmmssStr(null));
			right.setUpdateTime(DateUtils.date2yyyyMMddHHmmssStr(null));
			insertRet = rightDao.insert(right);
			if(insertRet==1){
				//添加成功，修改父节点信息
				rightDao.updateLeafAndIcon(right.getPid(),"0",Const.RIGHT_CION_DEFAULT);
				//更新内存中相关数据
//				GlobalCacheUtils.updateGlobalCache(right, 0);
			}else{
				throw new Exception();
			}
		} catch (Exception e) {
			throw e;
		}
		return insertRet;
	}
	
	/**
	 * 修改权限
	 */
	@Transactional(readOnly=false)
	public int editRight(SysRight right) throws Exception{
		int insertRet = 0;
		try {
			right.setUpdateTime(DateUtils.date2yyyyMMddHHmmssStr(null));
			insertRet = rightDao.update(right);
			if(insertRet==1){
				//更新menuList和orgTreeList
//				GlobalCacheUtils.updateGlobalCache(right, 1);
			}else{
				throw new Exception();
			}
		} catch (Exception e) {
			throw e;
		}
		return insertRet;
	}

	/**
	 * 根据权限登录名查询权限的信息
	 */
	@Transactional(readOnly=true)
	public SysRight queryByRightName(String rightName) {
//		return rightDao.getByWhere("right_name = ?",new Object[]{rightName});
		return null;
	}

	/**
	 * 查询权限分页信息
	 */
	@Transactional(readOnly=true)
	public Map<String, Object> queryRightList(Map<String, String> params) {
		return rightDao.selectRightList(params);
	}


	/**
	 * 删除权限
	 */
	@Transactional(readOnly=false)
	public boolean removeRights(Map<String, String> params) throws Exception{
		boolean result = false;
//		try{
//			String ids = params.get("ids");
//			SysRight right = rightDao.get(ids);
//			int c = rightDao.selectRoleRightCountByRightId(ids);
////			int e = rightDao.batchDelete(ids);
////			if(e==c+1){
////				List<?> rlist = rightDao.findAllList("pid=?",new Object[]{right.getPid()},"");
////				if(null==rlist||rlist.size()<=0){
////					//添加成功，修改父节点信息
////					rightDao.updateLeafAndIcon(right.getPid(),"1",Const.RIGHT_CION_HTML);
////				}
//				//更新menuList和orgTreeList
////				GlobalCacheUtils.updateGlobalCache(right, 2);
//			}else{
//				throw new Exception();
//			}
//			result = (e==c+1);
//		}catch(Exception e){
//			throw e;
//		}
		return result;
	}

	/**
	 * 根据主键查询权限信息
	 */
	@Transactional(readOnly=true)
	public SysRight queryById(String id) {
		return rightDao.get(id);
	}

	/**
	 * 查询用户权限信息
	 */
	@Transactional(readOnly=true)
	public List<SysRight> queryRights(String id) {
		return rightDao.selectRights(id);
	}

}
