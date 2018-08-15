package com.hanlet.config.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * 
 * @author xm
 * 2018年6月1日
 */
@JsonSerialize(using = CustomOauthExceptionSerializer.class)
public class CustomOauthException extends OAuth2Exception {
	private static final long serialVersionUID = 1139367709726830192L;

	public CustomOauthException(String msg) {
        super(msg);
    }
}
