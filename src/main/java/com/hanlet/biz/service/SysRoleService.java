package com.hanlet.biz.service;

import java.util.List;

import com.hanlet.biz.entity.SysRole;

public interface SysRoleService extends BaseService<SysRole, String>{

	List<SysRole> findByRoleName(String roleName);

}
