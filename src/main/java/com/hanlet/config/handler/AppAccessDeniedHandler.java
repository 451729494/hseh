package com.hanlet.config.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanlet.util.Result;
import com.hanlet.util.ResultStatus;


@Component("appAccessDeniedHandler")
public class AppAccessDeniedHandler implements AccessDeniedHandler {
	
	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		response.setContentType("application/json;charset=UTF-8");
		Result<String> result = new Result<String>();
		result.setStatus(ResultStatus.PERMISSIONDENY.getStatus());
		result.setDesc(ResultStatus.PERMISSIONDENY.getDesc());
		response.getWriter().write(objectMapper.writeValueAsString(result));
	}

}
