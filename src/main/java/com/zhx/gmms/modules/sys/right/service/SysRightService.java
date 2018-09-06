package com.zhx.gmms.modules.sys.right.service;

import java.util.List;
import java.util.Map;

import com.zhx.gmms.modules.sys.right.bean.MenuData;
import com.zhx.gmms.modules.sys.right.bean.SysRight;

public interface SysRightService {

	/**
	 * 查询用户权限信息
	 * @param id
	 * @return
	 */
	List<SysRight> queryRights(String id);

	/**
	 * 获取权限列表
	 * @return
	 */
	List<SysRight> findList(SysRight sysRight);

	/**
	 * 获取权限所属角色，并查出所有角色，标注权限所拥有的角色
	 * @param rightId
	 * @return
	 */
	List<Map<String, Object>> findRightRoles(String rightId);

	/**
	 * 删除权限
	 * @param rightId
	 * @return
	 */
	boolean removeRight(String id) throws Exception;

	/**
	 * 保存顶部权限，前端页面新建菜单的形式。以MenuData的类型
	 * @param md
	 * @return
	 */
	boolean saveTopRightByMenuData(MenuData md) throws Exception;

	/**
	 * 保存子权限，前端页面新建菜单的形式。以MenuData的类型
	 * @param md
	 * @return
	 */
	boolean saveSubRightByMenuData(MenuData md) throws Exception;

	/**
	 * 更新顶部菜单顺序
	 * @param list
	 * @return
	 */
	boolean updateTopOrder(List<Map<String,Object>> list) throws Exception;

}
