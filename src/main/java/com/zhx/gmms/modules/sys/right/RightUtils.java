package com.zhx.gmms.modules.sys.right;

import java.util.ArrayList;
import java.util.List;

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
}
