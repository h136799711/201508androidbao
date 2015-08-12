package com.jys.bluetoothproj.bean;

/**
 * Access_token类
 * 获取access_token时服务器返回的json格式数据封装成的类
 * @author Administrator
 *
 */
public class Access_token {
	
	private int code;
	private Info info;
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}
}
