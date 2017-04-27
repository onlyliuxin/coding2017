package com.louis.action;

public class LYLoginAction {
	private String message;
	public boolean login(String name, String password) {
		if (name.equals("test") && password.equals("123456")) {
			this.message = "login successful";
			return true;
		}
		this.message = "login failed, please check your user/pwd";
		return false;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
