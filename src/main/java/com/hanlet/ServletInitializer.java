package com.hanlet;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(HanletQuoteV1Application.class);
	}
	
	public static void main(String[] args) {
		String fileName = "java.class.apk";
		
		System.out.println(fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()));
	}

}
