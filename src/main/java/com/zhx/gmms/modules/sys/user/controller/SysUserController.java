package com.zhx.gmms.modules.sys.user.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhx.gmms.frame.BaseController;
import com.zhx.gmms.frame.Const;
import com.zhx.gmms.modules.sys.theme.bean.SysTheme;
import com.zhx.gmms.modules.sys.user.bean.SysUser;
import com.zhx.gmms.modules.sys.user.service.SysUserService;
import com.zhx.gmms.utils.DESUtils;

/**
 * 用户controller
 * @author xwl
 */
@Controller
@RequestMapping("/user")
public class SysUserController extends BaseController {

	@Autowired
	private SysUserService sysUserService;
	
	/**
	 * 初始化名额
	 * @param meetingId
	 */
//	@RequestMapping("/init/quota")
//	public void importRR() throws Exception{
//		sysUserService.initQuota();
//	}
	
	/**
	 * 跳转到用户列表页面
	 * @param user
	 * @return
	 */
	@GetMapping("/list")
	public String findList(SysUser user,Model model){
		List<SysUser> ulist = sysUserService.findList(null);
		model.addAttribute("userCount", ulist.size());
		return "user/list";
	}
	
	/**
	 * 用户pagelist信息
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/pagelist")
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
	@PostMapping("/updateTheme/{userId}")
	@ResponseBody
	public String updateTheme(SysTheme sysTheme,@PathVariable String userId) throws Exception{
		SysTheme stm = sysUserService.updateTheme(sysTheme,userId);
		//stm不为null，更新成功，更新session中的主题信息
		if(null!=stm){
			request.getSession().setAttribute(Const.SESSION_THEME, stm);
			request.getSession().setAttribute(Const.SESSION_THEME_JSON, toJson(stm));
		}
		return toJson(stm);
	}
	
	/**
	 * 获取用户所属角色，并查出所有角色，标注用户所拥有的角色
	 * @param userId
	 * @return
	 */
	@GetMapping("/role")
	@ResponseBody
	public String userRoles(String userId){
		List<Map<String, Object>> list = sysUserService.findUserRoles(userId);
		return toJsonKV(null!=list&&list.size()>0,"auth",list);
	}
	
	/**
	 * 新增或更新用户信息
	 * @return
	 */
	@PostMapping("/save")
	@ResponseBody
	public String saveOrUpdateUser(SysUser user,String[] roleIds,String confirm) throws Exception{
		//密码和确认密码不一致
		if(!user.getPassword().equals(confirm)){
			return toJson(false,"用户密码和确认密码不一致！");
		}
		Boolean r = sysUserService.saveOrUpdateUser(user,roleIds);
		return toJsonKV(r,"user",user);
	}
	
	/**
	 * 删除用户
	 * @param userId
	 * @return
	 */
	@PostMapping("/remove")
	@ResponseBody
	public String removeUser(String userId) throws Exception{
		int r = sysUserService.removeUser(userId);
		return toJson(r==1);
	}
	
	/**
	 * 禁用启用用户
	 * @param user
	 * @return
	 */
	@PostMapping("/updateUseStatus")
	@ResponseBody
	public String updateUseStatus(SysUser user) throws Exception{
		int r = sysUserService.editUser(user);
		return toJson(r==1);
	}
	
	/**
	 * 验证密码是否正确
	 * @return
	 * @throws Exception 
	 */
	@GetMapping("/validPassword")
	@ResponseBody
	public String validPassword(SysUser user) throws Exception{
		SysUser cu = sysUserService.getUser(user.getId());
		String cup = DESUtils.decrypt(cu.getPassword());
		return toJsonKV(true, "valid", user.getPassword().equals(cup));
	}
	
	/**
	 * 用户修改密码
	 * @throws Exception 
	 */
	@PostMapping("/updatePassword")
	@ResponseBody
	public String updatePassword(SysUser user) throws Exception{
		user.setPassword(DESUtils.encrypt(user.getPassword()));
		int r = sysUserService.editUser(user);
		return toJson(r==1);
	}
}
