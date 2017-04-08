package com.coderising.litestruts;

public class LoginAction {

	private String name;
	private String passWord;
	private String message;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String execute(){
		if ("test".equals(name) && "1234".equals(passWord)) {
			this.message = "login successful";
			return "success";
		}
		this.message = "login failed,please check your user/pwd";
		return "fail";
	}
}
