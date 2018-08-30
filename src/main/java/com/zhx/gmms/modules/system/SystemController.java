package com.zhx.gmms.modules.system;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhx.gmms.frame.BaseController;
import com.zhx.gmms.frame.Const;
import com.zhx.gmms.modules.sys.user.bean.SysUser;

@Controller
@RequestMapping("/system")
public class SystemController extends BaseController {

	@GetMapping("/locked")
	public String locked(HttpServletRequest req,Model model){
		//当前登录用户的userCode
		SysUser user = (SysUser) req.getSession().getAttribute(Const.SESSION_USER);
		model.addAttribute("userCode", user.getUserCode());
		//清空session
		clearSession(req);
		return "system/locked";
	}
	
	@GetMapping("/settings")
	public String setting(){
		return "system/settings/display";
	}
}
