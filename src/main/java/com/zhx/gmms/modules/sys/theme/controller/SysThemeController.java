package com.zhx.gmms.modules.sys.theme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.zhx.gmms.frame.BaseController;
import com.zhx.gmms.modules.sys.theme.service.SysThemeService;

@Controller
public class SysThemeController extends BaseController {

	@Autowired
	private SysThemeService sysThemeService;
	
}
