package com.zhx.gmms.modules.sys.theme.service;

import com.zhx.gmms.modules.sys.theme.bean.SysTheme;

public interface SysThemeService {

	SysTheme get(String id);

	int saveSysTheme(SysTheme sysTheme);

	int editSysTheme(SysTheme sysTheme);
}
