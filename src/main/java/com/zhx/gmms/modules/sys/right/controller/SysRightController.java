package com.zhx.gmms.modules.sys.right.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhx.gmms.frame.BaseController;
import com.zhx.gmms.frame.Const;
import com.zhx.gmms.modules.sys.right.RightUtils;
import com.zhx.gmms.modules.sys.right.bean.MenuData;
import com.zhx.gmms.modules.sys.right.bean.SysRight;
import com.zhx.gmms.modules.sys.right.service.SysRightService;

@Controller
@RequestMapping("/right")
public class SysRightController extends BaseController {
	
	private Logger logger = LoggerFactory.getLogger(SysRightController.class);
	
	@Autowired
	private SysRightService rightService;

	/**
	 * 跳转到权限列表页面
	 * @return
	 */
	@GetMapping("/list")
	public String list(){
		return "right/list";
	}
	
	/**
	 * 获取权限列表
	 * @param request
	 * @param response
	 * @param params
	 * @return
	 */
	@PatchMapping("/list")
	@ResponseBody
	public String rightList(SysRight sysRight){
		logger.info("获取权限列表...");
		List<SysRight> rlist = rightService.findList(sysRight);
		return toJson(RightUtils.menuTreeList(rlist,Const.RIGHT_ROOT));
	}
	
	/**
	 * 保存权限，前端页面新建菜单的形式。以MenuData的类型
	 * @param menu
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/save")
	@ResponseBody
	public String saveRight(String menu) throws Exception{
		MenuData md = objectMapper.readValue(menu, MenuData.class);
		//保存权限，前端页面新建菜单的形式。以MenuData的类型
		int r = rightService.saveRightByMenuData(md);
		return toJson(r==md.getAuth().size()+1);
	}
	
	/**
	 * 删除权限
	 * @param rightId 权限id
	 * @return
	 */
	@PostMapping("/delete/{id}")
	@ResponseBody
	public String deleteRight(@PathVariable String id){
		logger.info("删除权限...权限id："+id);
		int i = rightService.removeRight(id);
		return toJson(i>0);
	}
	
	/**
	 * 获取权限所属角色，并查出所有角色，标注权限所拥有的角色
	 * @param rightId 权限id
	 * @return
	 */
	@GetMapping("/roles")
	@ResponseBody
	public String roles(String rightId){
		logger.info("获取权限角色，并标出权限所属的角色是哪些...权限id:"+rightId);
		List<Map<String,Object>> ret = rightService.findRole(rightId);
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("auth", ret);
		return toJson(null!=ret&&ret.size()>0, result);
	}
}
