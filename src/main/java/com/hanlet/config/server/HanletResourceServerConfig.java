package com.hanlet.config.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.hanlet.config.AuthExceptionEntryPoint;
import com.hanlet.config.PermitAllSecurityConfig;
import com.hanlet.config.handler.AppAccessDeniedHandler;
import com.hanlet.config.handler.AppLogoutHandler;
import com.hanlet.config.handler.AppLogoutSuccessHandler;

/**
 * 
 * @author xm
 * 2018年6月1日
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class HanletResourceServerConfig extends ResourceServerConfigurerAdapter {

    /**
     * 自定义登录成功处理器
     */
    @Autowired
    private AuthenticationSuccessHandler appLoginInSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler appLoginFailureHandler;
    
    @Autowired
    private AppLogoutSuccessHandler appLogoutSuccessHandler;
    
    @Autowired
    private AppLogoutHandler appLogoutHandler;
    
    @Autowired
    private AppAccessDeniedHandler appAccessDeniedHandler;
    
    @Autowired
    private AuthExceptionEntryPoint authExceptionEntryPoint;
    
    @Autowired
    private PermitAllSecurityConfig permitAllSecurityConfig;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        // @formatter:off
        http.formLogin()
        		//登录处理
        		.loginProcessingUrl("/login").passwordParameter("password").usernameParameter("username").successHandler(appLoginInSuccessHandler).failureHandler(appLoginFailureHandler)
                .and()
                //异常处理
                .exceptionHandling().authenticationEntryPoint(authExceptionEntryPoint).accessDeniedHandler(appAccessDeniedHandler)
                .and()
                .apply(permitAllSecurityConfig)
                .and()
                .authorizeRequests()
                .antMatchers("/permitAll","/user/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/logout").addLogoutHandler(appLogoutHandler).logoutSuccessHandler(appLogoutSuccessHandler)
                .and()
                .csrf().disable();

        // @formatter:ON
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.authenticationEntryPoint(authExceptionEntryPoint);
    }
    
    
}
