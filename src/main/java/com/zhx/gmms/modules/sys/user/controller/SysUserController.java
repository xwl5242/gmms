package com.zhx.gmms.modules.sys.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhx.gmms.frame.BaseController;
import com.zhx.gmms.modules.sys.theme.bean.SysTheme;
import com.zhx.gmms.modules.sys.user.bean.SysUser;
import com.zhx.gmms.modules.sys.user.service.SysUserService;

/**
 * 用户controller
 * @author xwl
 */
@Controller
public class SysUserController extends BaseController {

	@Autowired
	private SysUserService sysUserService;
	
	/**
	 * 主键获取用户信息
	 * @param id 主键
	 * @return
	 */
	@GetMapping("/{id}")
	public SysUser getUser(@PathVariable("id") String id){
		return sysUserService.get(id);
	}
	
	/**
	 * 跳转到用户列表页面
	 * @param user
	 * @return
	 */
	@GetMapping("/user/list")
	public String findList(SysUser user){
		return "user/list";
	}
	
	/**
	 * 用户pagelist信息
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/user/pagelist")
	@ResponseBody
	public String userList(SysUser user) throws Exception{
		List<SysUser> list = sysUserService.findList(user);
		return returnDataTables(list);
	}
	
	/**
	 * 用户自定义更新主题信息
	 * @param sysTheme
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/user/updateTheme/{userId}")
	@ResponseBody
	public String updateTheme(SysTheme sysTheme,@PathVariable String userId) throws Exception{
		int ret = sysUserService.updateTheme(sysTheme,userId);
		return returnJsonStr(ret>=1);
	}
}
