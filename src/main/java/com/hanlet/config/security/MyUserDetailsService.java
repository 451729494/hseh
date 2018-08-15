package com.hanlet.config.security;


import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.hanlet.biz.entity.SysRole;
import com.hanlet.biz.entity.SysUser;
import com.hanlet.biz.service.SysUserService;

/**
 * 
 * @author xm
 * 2018年6月1日
 */
@Component("myUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private SysUserService userService;

    /**
     * 根据用户名获取登录用户信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userService.findByUsername(username);
        if(user == null){
             throw new UsernameNotFoundException("用户名："+ username + "不存在！");
        }
        Collection<SimpleGrantedAuthority> collection = new HashSet<SimpleGrantedAuthority>();
        Iterator<SysRole> iterator =  user.getRoles().iterator();
        while (iterator.hasNext()){
            collection.add(new SimpleGrantedAuthority(iterator.next().getRoleName()));
        }
        return new User(username, user.getPassword(), collection);
    }
    
    public static void main(String[] args) {
    	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    	
    	System.out.println(passwordEncoder.encode("123456"));
	}
}
