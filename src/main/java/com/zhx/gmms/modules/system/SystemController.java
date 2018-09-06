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

	@GetMapping("/locked")
	public String locked(HttpServletRequest req,Model model){
		//当前登录用户的userCode
		model.addAttribute("userCode", UserUtils.getCurUserCode());
		//清空session
		clearSession(req);
		return "system/locked";
	}
	
	@GetMapping({"/settings","/account/display"})
	public String setting(){
		return "system/settings/display";
	}
	
	@GetMapping("/account")
	public String account(){
		return "system/account/account";
	}
	
	@GetMapping("/account/log")
	public String account_log(){
		return "system/account/log";
	}
	
	@GetMapping("/account/password")
	public String account_password(){
		return "system/account/password";
	}
	
}
