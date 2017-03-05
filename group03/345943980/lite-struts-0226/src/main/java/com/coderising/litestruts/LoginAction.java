package com.coderising.litestruts;

public class LoginAction {

	private String name;
	private String password;
	private String mssage;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMssage() {
		return mssage;
	}

	public void setMssage(String mssage) {
		this.mssage = mssage;
	}

	public String execute() {
		if (this.getName().equals("test") && this.getPassword().equals("1234")) {
			this.setMssage("login successful");
			return "success";
		} else {
			this.setMssage("login failed,please check your user/pwd");
			return "fail";
		}
	}
}
