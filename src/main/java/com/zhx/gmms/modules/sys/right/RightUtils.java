package com.zhx.gmms.modules.sys.right;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhx.gmms.modules.sys.right.bean.SysRight;

public class RightUtils {

	/**
	 * 生成父子结构的菜单树
	 * @param menuList 数据库查询结果
	 * @param parentId 父ID
	 * @return
	 */
	public static List<SysRight> menuTreeList(List<SysRight> menuList, String parentId){
		List<SysRight> childMenu = new ArrayList<SysRight>();  
		if(null!=menuList&&menuList.size()>0){
			for (SysRight right : menuList) {  
				String menuId = right.getId();
				String pid = right.getPid();
				if (parentId.equals(pid)) {  
					List<SysRight> c_node = menuTreeList(menuList, menuId);  
					right.setChildNode(c_node);
					childMenu.add(right);  
				}  
			}  
		}
        return childMenu;  
	}
	
	/**
	 * 权限树
	 * @param menuList
	 * @param parentId
	 * @return
	 */
	public static List<Map<String,Object>> rightTreeList(List<SysRight> menuList, String parentId){
		List<Map<String,Object>> tree = new ArrayList<Map<String,Object>>();
		if(null!=menuList&&menuList.size()>0){
			for (SysRight right : menuList) {  
				Map<String,Object> node = new HashMap<String,Object>();
				if (parentId.equals(right.getPid())) {  
					node.put("layer", right.getId());
					node.put("icon", right.getIcon());
					node.put("tenantId", "8");
					node.put("id", right.getId());
					node.put("text", right.getRightName());
					List<Map<String,Object>> c_node = rightTreeList(menuList, right.getId());  
					node.put("children", c_node);
					if(null!=right.getState()&&"true".equals(right.getState())){
						Map<String,Object> state = new HashMap<String, Object>();
						state.put("selected", true);
						node.put("state",state);
					}
					tree.add(node);  
				}  
			}  
		}
		return tree;
	}
}
