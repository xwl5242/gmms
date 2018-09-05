package com.zhx.gmms.modules.sys.role.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhx.gmms.frame.BaseController;
import com.zhx.gmms.frame.Const;
import com.zhx.gmms.modules.sys.right.RightUtils;
import com.zhx.gmms.modules.sys.right.bean.SysRight;
import com.zhx.gmms.modules.sys.role.service.SysRoleService;
import com.zhx.gmms.modules.sys.user.bean.SysUser;

@Controller
@RequestMapping("/role")
public class SysRoleController extends BaseController {
	
	private Logger logger = LoggerFactory.getLogger(SysRoleController.class);
	
	@Autowired
	private SysRoleService roleService;

	/**
	 * 查询每个角色信息以及该角色下的用户数
	 * @return
	 */
	@GetMapping("/usercounts")
	@ResponseBody
	public String roleUserCounts(){
		logger.info("");
		List<Map<String,String>> list = roleService.roleUserCounts();
		return toJson(list);
	}
	
	/**
	 * 根据roleId查询该角色下的用户list信息
	 * @param roleId
	 * @return
	 */
	@GetMapping("/user")
	@ResponseBody
	public String roleUser(String roleId){
		List<SysUser> userList = roleService.findUserByRole(roleId);
		return returnDataTables(userList);
	}
	
	/**
	 * 查询权限树
	 */
	@GetMapping("/rights")
	@ResponseBody
	public String rights(String roleId){
		List<SysRight> rightList = roleService.findRightByRole(roleId);
		return toJson(RightUtils.rightTreeList(rightList, Const.RIGHT_ROOT));
	}
	
	/**
	 * 新增角色
	 * @param roleName 角色名称
	 * @param roleAuth 角色所拥有的权限的id
	 * @return
	 */
	@PostMapping("/save")
	@ResponseBody
	public String saveRole(String roleId,String roleName,String[] roleAuth){
		String id = roleService.saveRole(roleId,roleName,roleAuth);
		return toJsonKV(null!=id,"id",id);
	}
	
	/**
	 * 删除角色
	 * @return
	 */
	@PostMapping("/delete")
	@ResponseBody
	public String deleteRole(String roleId){
		int r = roleService.deleteRole(roleId);
		return toJson(r>=1);
	}
}
