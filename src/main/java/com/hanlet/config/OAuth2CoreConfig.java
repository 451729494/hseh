package com.hanlet.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.hanlet.config.properties.OAuth2Properties;

/**
 * 
 * @author xm
 * 2018年6月1日
 */
@Configuration
@EnableConfigurationProperties(OAuth2Properties.class)
public class OAuth2CoreConfig {
}
