package com.hanlet.util;

public enum ResultStatus {

	SUCCESS(200,"请求成功"),ERROR(500,"系统错误"),UNAUTH(401,"未经授权"),PERMISSIONDENY(403,"权限不足");
	
	private Integer status;
	
	private String desc;
	
	private ResultStatus(Integer status, String desc){
	  this.status = status;
	  this.desc = desc;
	}

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
	
	
	
}
