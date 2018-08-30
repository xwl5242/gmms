package com.zhx.gmms.modules.sys.right.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.zhx.gmms.modules.sys.right.bean.SysRight;
import com.zhx.gmms.modules.sys.right.dao.SysRightDao;
import com.zhx.gmms.modules.sys.right.service.SysRightService;

@Service
@EnableTransactionManagement
public class SysRightServiceImpl implements SysRightService {

	@Autowired
	private SysRightDao rightDao;
	

	/**
	 * 查询用户权限信息
	 */
	@Transactional(readOnly=true)
	public List<SysRight> queryRights(String userId) {
		return rightDao.selectRights(userId);
	}

	/**
	 * 获取所有权限信息
	 */
	@Override
	public List<SysRight> findList() {
		return rightDao.selectList();
	}

}
