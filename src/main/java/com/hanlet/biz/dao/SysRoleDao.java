package com.hanlet.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hanlet.biz.dao.base.BaseDao;
import com.hanlet.biz.entity.SysRole;

@Repository
public interface SysRoleDao extends BaseDao<SysRole, String> {

	List<SysRole> findByRoleName(String roleName);

}
