package com.hanlet.biz.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.ExampleMatcher.NullHandler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hanlet.biz.dao.SysUserDao;
import com.hanlet.biz.entity.SysUser;
import com.hanlet.biz.service.SysUserService;

@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser, String> implements SysUserService {

	private SysUserDao userDao;
	
	@Autowired
	public void setUserDao(SysUserDao userDao) {
		this.userDao = userDao;
		this.setGenericDAO(userDao);
	}

	@Override
	public SysUser findByUsername(String username) {
		
		return userDao.findByUsername(username);
	}

	@Override
	public List<SysUser> findAll() {
		return userDao.findAll();
	}

	@Override
	public Page<SysUser> findPage(Pageable pageable, SysUser sysUser) {
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("username", GenericPropertyMatchers.contains()).withNullHandler(NullHandler.IGNORE);
		Example<SysUser> ex = Example.of(sysUser, matcher);
		return userDao.findAll(ex, pageable);
	}

	@Override
	public boolean updatePassword(String userId, String password) {
		try {
			SysUser user = userDao.findOne(userId);
			user.setPassword(new BCryptPasswordEncoder().encode(password));
			user.setUpdateDateTime(new Date());
			userDao.save(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
	}
}
