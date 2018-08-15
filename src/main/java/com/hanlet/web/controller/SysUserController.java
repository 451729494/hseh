package com.hanlet.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.hanlet.biz.entity.SysRole;
import com.hanlet.biz.entity.SysUser;
import com.hanlet.biz.service.SysRoleService;
import com.hanlet.biz.service.SysUserService;
import com.hanlet.util.Result;
import com.hanlet.util.ResultStatus;

@Controller
@RequestMapping(value = "/user")
public class SysUserController {

	@Autowired
	private SysUserService userService;
	
	@Autowired
	private SysRoleService roleService;
	
//	@Autowired
//	private SysUserRoleService userRoleService;

	@ResponseBody
	@GetMapping(value="/getInfo/{username}")
	public Result<Map<String, Object>> getUserInfo(@PathVariable(value="username", required=true)String username) {
		
//		SysRole role = new SysRole();
//		role.setRoleName("ROLE_ADMIN");
//		role.setRoleText("管理员");
//		role.setCreateDateTime(new Date());
//		role.setUpdateDateTime(new Date());
//		roleService.save(role);
//		
//		SysRole role1 = new SysRole();
//		role1.setRoleName("ROLE_USER");
//		role1.setRoleText("普通用户");
//		role1.setCreateDateTime(new Date());
//		role1.setUpdateDateTime(new Date());
//		roleService.save(role1);
//		
//		SysUser user = new SysUser();
//		user.setCreateDateTime(new Date());
//		user.setPassword(new BCryptPasswordEncoder().encode("123456"));
//		user.setUpdateDateTime(new Date());
//		user.setUsername("administrator");
//		
//		Iterable<SysRole> rolesList = roleService.findAll();
//		List<SysRole> roles = Lists.newArrayList(rolesList);
//		
//		user.setRoles(roles);
//		userService.save(user);
//		
		
		Result<Map<String, Object>> result = new Result<Map<String, Object>>();
		SysUser user = userService.findByUsername(username);
		List<SysRole> sysRoles = user.getRoles();
		List<String> roleNames = new ArrayList<String>();
		for(SysRole temp : sysRoles){
			roleNames.add(temp.getRoleName());
		}
		Map<String, Object> resultInfo = new HashMap<String, Object>();
		resultInfo.put("roles", roleNames);
		resultInfo.put("username", user.getUsername());
		resultInfo.put("userId", user.getSysUserId());
		
		result.setData(resultInfo);
		return result;
	}
	
	@ResponseBody
	@PostMapping
	public Result<SysUser> saveSysUser(@RequestBody SysUser sysUser){
		Result<SysUser> result = new Result<SysUser>();
		try {
			
			Iterable<SysRole> rolesList = roleService.findByRoleName("ROLE_USER");
			List<SysRole> roles = Lists.newArrayList(rolesList);
			
			sysUser.setRoles(roles);
			sysUser.setPassword(new BCryptPasswordEncoder().encode(sysUser.getPassword()));
			
			sysUser.setCreateDateTime(new Date());
			sysUser.setUpdateDateTime(new Date());
			SysUser returnSysUser = userService.save(sysUser);
			result.setData(returnSysUser);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(ResultStatus.ERROR.getStatus());
			result.setDesc(ResultStatus.ERROR.getDesc());
			return result;
		}
	}
	
	@ResponseBody
	@DeleteMapping(value="/{id}")
	public Result<SysUser> deleteCliente(@PathVariable(name="id",required=true) String sysUserId){
		Result<SysUser> result = new Result<SysUser>();
		try {
			userService.deleteById(sysUserId);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(ResultStatus.ERROR.getStatus());
			result.setDesc(ResultStatus.ERROR.getDesc());
			return result;
		}
	}
	
	@ResponseBody
	@GetMapping(value="/{id}")
	public Result<SysUser> getCliente(@PathVariable(name="id",required=true) String sysUserId){
		Result<SysUser> result = new Result<SysUser>();
		try {
			SysUser client = userService.getById(sysUserId);
			result.setData(client);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(ResultStatus.ERROR.getStatus());
			result.setDesc(ResultStatus.ERROR.getDesc());
			return result;
		}
	}
	
	@ResponseBody
	@PutMapping
	public Result<SysUser> updateSysUser(@RequestBody SysUser sysUser){
		Result<SysUser> result = new Result<SysUser>();
		try {
			SysUser source = userService.getById(sysUser.getSysUserId());
			sysUser.setCreateDateTime(source.getCreateDateTime());
			sysUser.setUpdateDateTime(new Date());
			SysUser returnSysUser = userService.save(sysUser);
			result.setData(returnSysUser);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(ResultStatus.ERROR.getStatus());
			result.setDesc(ResultStatus.ERROR.getDesc());
			return result;
		}
	}
	
	@ResponseBody
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PostMapping(value="/list/{page}/{size}")
	public Result<Page<SysUser>> listSysUser(@PathVariable(value = "page",required=true) 
	String page, @PathVariable(value = "size") String size, 
			@RequestBody SysUser sysUser){
		Result<Page<SysUser>> result = new Result<Page<SysUser>>();
		try {
			Pageable pageable = new PageRequest(Integer.parseInt(page)-1, Integer.parseInt(size), 
					Sort.Direction.DESC,"createDateTime");
			Page<SysUser> pageContent = userService.findPage(pageable,sysUser);
			result.setData(pageContent);
			return result;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			result.setStatus(ResultStatus.ERROR.getStatus());
			result.setDesc(ResultStatus.ERROR.getDesc());
			return result;
		}
		
	}
	@ResponseBody
	@PostMapping(value="/updatepwd")
	public Result<Map<String, Object>> updatePassword(@RequestBody Map<String, String> params){
		Result<Map<String, Object>> result = new Result<Map<String, Object>>();
		String password = params.get("password");
		String userId = params.get("userId");
		boolean flag = userService.updatePassword(userId, password);
		if(flag) {
			result.setData(null);
			return result;
		} else {
			result.setStatus(ResultStatus.ERROR.getStatus());
			result.setDesc(ResultStatus.ERROR.getDesc());
			return result;
		}
		
		
	}
	
	

}
