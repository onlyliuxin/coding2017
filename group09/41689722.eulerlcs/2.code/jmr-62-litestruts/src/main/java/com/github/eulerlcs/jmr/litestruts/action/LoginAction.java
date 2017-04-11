package com.github.eulerlcs.jmr.litestruts.action;

import lombok.Getter;
import lombok.Setter;

/**
 * 这是一个用来展示登录的业务类， 其中的用户名和密码都是硬编码的。
 * 
 * @author liuxin
 *
 */
public class LoginAction {
	@Setter
	@Getter
	private String name;
	@Setter
	@Getter
	private String password;
	@Getter
	private String message;

	public String execute() {
		if ("test".equals(name) && "1234".equals(password)) {
			this.message = "login successful";
			return "success";
		}
		this.message = "login failed,please check your user/pwd";
		return "fail";
	}
}
