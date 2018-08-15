package com.hanlet.biz.service;

public interface RedisService {

	public boolean set(final String key, final String value);
	
	public String get(final String key);
	
	public boolean expire(final String key, long expire);
	
	public String lpop(final String key);
}
