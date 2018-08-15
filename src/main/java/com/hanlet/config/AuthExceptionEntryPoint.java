package com.hanlet.config;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanlet.util.Result;
import com.hanlet.util.ResultStatus;

/**
 * 
 * @author xm
 * 2018年6月1日
 */
@Component(value = "authExceptionEntryPoint")
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException)
            throws  ServletException {
    	Result<String> result = new Result<String>();
        result.setStatus(ResultStatus.UNAUTH.getStatus());
        result.setDesc(ResultStatus.UNAUTH.getDesc());
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getOutputStream(), result);
        } catch (Exception e) {
            throw new ServletException();
        }
    }
}