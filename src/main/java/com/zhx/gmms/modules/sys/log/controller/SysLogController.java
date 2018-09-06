package com.zhx.gmms.modules.sys.log.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.zhx.gmms.frame.BaseController;
import com.zhx.gmms.modules.sys.log.bean.SysLog;
import com.zhx.gmms.modules.sys.log.service.SysLogService;
import com.zhx.gmms.modules.sys.user.bean.SysUser;

@Controller
@RequestMapping("/log")
public class SysLogController extends BaseController {
	
	@Autowired
	private SysLogService logService;

	@GetMapping("/list")
	public String list(Model model){
		List<SysUser> list = logService.findAllUser();
		model.addAttribute("users", list);
		return "log/log";
	}
	
	@GetMapping("/pagelist")
	@ResponseBody
	public String pageList(String startDate,String endDate,String userId,int start,int limit,String dir){
		Map<String,String> wheres = new HashMap<String, String>();
		wheres.put("startDate", startDate);
		wheres.put("endDate", endDate);
		wheres.put("userId", userId);
		Page<SysLog> logPage = logService.findPageList(wheres,start,limit,dir);
		Map<String,Object> map = new HashMap<String, Object>();
		if(null!=logPage){
			map.put("total", logPage.getTotal());
			map.put("data", logPage.getResult());
		}
		return toJson(null!=logPage,map);
	}
}
