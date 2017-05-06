package com.sprint.litestruts;

/**
 * 这是一个展示业务逻辑的类，其中用户名是硬编码
 * @author xingzhaohu
 */

public class LoginAction {
	private String name;
	private String password;
	private String message;

	public String execute() {
		if ("test".equals(name) && "1234".equals(password)) {
			this.message = "login successful";
			return "success";
		}
		this.message = "login failed,please check your user/pwd";
		return "fail";
	}
	public void setName(String name) {
		this.name = name;
	}	

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getMessage() {
		return message;
	}
}
