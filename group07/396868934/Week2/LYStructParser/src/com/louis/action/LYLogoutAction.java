package com.louis.action;

public class LYLogoutAction {
	private String message;
	public boolean logout(String name, String password) {
		if (name.equals("test") && password.equals("123456")) {
			this.message = "logout successful";
			return true;
		}
		this.message = "logout failed, please check your user/pwd";
		return false;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
