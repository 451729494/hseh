package com.hanlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.hanlet.biz.dao.base.BaseRepositoryFactoryBean;

@EnableCaching
@SpringBootApplication
@EnableJpaRepositories(basePackages = { "com.hanlet.biz.dao" },
	// 指定自己的工厂类
	repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
public class HanletQuoteV1Application {

	public static void main(String[] args) {
		SpringApplication.run(HanletQuoteV1Application.class, args);
	}
}
