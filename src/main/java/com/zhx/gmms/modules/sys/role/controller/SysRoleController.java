package com.zhx.gmms.modules.sys.role.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhx.gmms.frame.BaseController;
import com.zhx.gmms.modules.sys.role.service.SysRoleService;

@Controller
@RequestMapping("/role")
public class SysRoleController extends BaseController {
	
	private Logger logger = LoggerFactory.getLogger(SysRoleController.class);
	
	@Autowired
	private SysRoleService roleService;

}
