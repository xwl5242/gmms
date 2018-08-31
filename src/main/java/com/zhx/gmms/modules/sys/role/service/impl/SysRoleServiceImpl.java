package com.zhx.gmms.modules.sys.role.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.zhx.gmms.modules.sys.role.bean.SysRole;
import com.zhx.gmms.modules.sys.role.dao.SysRoleDao;
import com.zhx.gmms.modules.sys.role.service.SysRoleService;

@Service
@EnableTransactionManagement
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
	private SysRoleDao roleDao;

	@Transactional(readOnly=true)
	public List<SysRole> findList(SysRole sysRole) {
		return roleDao.findList(sysRole);
	}

}
