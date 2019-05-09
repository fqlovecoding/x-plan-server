package com.ffq.common;

import com.alibaba.fastjson.JSONObject;

import lombok.Data;

@Data
public class GlobalResponse<T> {
	private Integer code;
	private String msg;
	private T data;
	
	public static String ok(Object object) {
		GlobalResponse<Object> resp = new GlobalResponse<Object>();
		resp.setData(object);
		return JSONObject.toJSONString(resp);
	}
	
	public static String err(String msg,Integer code) {
		GlobalResponse<Object> resp = new GlobalResponse<Object>();
		resp.setMsg(msg);
		resp.setCode(code);
		return JSONObject.toJSONString(resp);
	}
}
