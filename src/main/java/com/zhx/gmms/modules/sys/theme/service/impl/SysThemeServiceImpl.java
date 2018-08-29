package com.zhx.gmms.modules.sys.theme.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.zhx.gmms.modules.sys.theme.bean.SysTheme;
import com.zhx.gmms.modules.sys.theme.dao.SysThemeDao;
import com.zhx.gmms.modules.sys.theme.service.SysThemeService;

@Service
@EnableTransactionManagement
public class SysThemeServiceImpl implements SysThemeService {
	
	@Autowired
	private SysThemeDao systheDao;

	@Transactional(readOnly=true)
	public SysTheme get(String id) {
		return systheDao.get(id);
	}

	@Transactional(readOnly=false)
	public int saveSysTheme(SysTheme theme) {
		return systheDao.insert(theme);
	}

	@Transactional(readOnly=false)
	public int editSysTheme(SysTheme theme) {
		return systheDao.update(theme);
	}

}
