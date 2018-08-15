package com.hanlet.util;

import java.util.Date;

/**
 * 
 * @author xm
 * 2018年6月1日
 * @param <T>
 */
public class Result<T> {
    private Integer status = ResultStatus.SUCCESS.getStatus();
    private String desc = ResultStatus.SUCCESS.getDesc();
    private String timestamp = String.valueOf(new Date().getTime());
    private T data;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getTimestamp() {
		return timestamp;
	}
//
//	public void setTimestamp(String timestamp) {
//		this.timestamp = timestamp;
//	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
    
    
}
