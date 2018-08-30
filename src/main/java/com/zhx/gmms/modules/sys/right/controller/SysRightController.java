package com.zhx.gmms.modules.sys.right.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhx.gmms.frame.BaseController;
import com.zhx.gmms.frame.Const;
import com.zhx.gmms.modules.sys.right.RightUtils;
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
	public String rightList(){
		logger.info("获取权限列表...");
		List<SysRight> rlist = rightService.findList();
		return toJson(RightUtils.menuTreeList(rlist,Const.RIGHT_ROOT));
	}
	
}
