package com.zhx.gmms.modules.system;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhx.gmms.frame.BaseController;
import com.zhx.gmms.modules.sys.user.UserUtils;

@Controller
@RequestMapping("/system")
public class SystemController extends BaseController {

	/**
	 * 跳转到锁屏页面
	 * @param req
	 * @param model
	 * @return
	 */
	@GetMapping("/locked")
	public String locked(HttpServletRequest req,Model model){
		//当前登录用户的userCode
		model.addAttribute("userCode", UserUtils.getCurUserCode());
		//清空session
		clearSession(req);
		return "system/locked";
	}
	
	/**
	 * 跳转到用户主题设置页面
	 * @return
	 */
	@GetMapping({"/settings","/account/display"})
	public String setting(){
		return "system/settings/display";
	}
	
	/**
	 * 跳转到账户管理页面
	 * @return
	 */
	@GetMapping("/account")
	public String account(){
		return "system/account/account";
	}
	
	/**
	 * 跳转到账户管理页面的日志子页面
	 * @return
	 */
	@GetMapping("/account/log")
	public String account_log(){
		return "system/account/log";
	}
	
	/**
	 * 跳转到账户管理页面的修改密码子页面
	 * @return
	 */
	@GetMapping("/account/password")
	public String account_password(){
		return "system/account/password";
	}
	
}
