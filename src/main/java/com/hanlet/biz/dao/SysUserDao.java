package com.hanlet.biz.dao;

import org.springframework.stereotype.Repository;

import com.hanlet.biz.dao.base.BaseDao;
import com.hanlet.biz.entity.SysUser;

@Repository
public interface SysUserDao extends BaseDao<SysUser, String> {

	public SysUser findByUsername(String username);

}
