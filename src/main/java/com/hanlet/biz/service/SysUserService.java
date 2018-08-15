package com.hanlet.biz.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hanlet.biz.entity.SysUser;

public interface SysUserService extends BaseService<SysUser, String>{

	public SysUser findByUsername(String username);

	public List<SysUser> findAll();

	public Page<SysUser> findPage(Pageable pageable, SysUser sysUser);

	public boolean updatePassword(String userId, String password);

}
