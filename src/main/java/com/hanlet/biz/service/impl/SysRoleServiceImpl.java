package com.hanlet.biz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanlet.biz.dao.SysRoleDao;
import com.hanlet.biz.entity.SysRole;
import com.hanlet.biz.service.SysRoleService;

@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole, String> implements SysRoleService {

	private SysRoleDao roleDao;

	@Autowired
	public void setRoleDao(SysRoleDao roleDao) {
		this.roleDao = roleDao;
		this.setGenericDAO(roleDao);
	}

	@Override
	public List<SysRole> findByRoleName(String roleName) {
		return roleDao.findByRoleName(roleName);
	}
	
	
}
