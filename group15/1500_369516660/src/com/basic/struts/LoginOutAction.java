package com.basic.struts;
/**
 * 退出的业务类
 * @author Jodie
 *
 */
public class LoginOutAction {

	private String name;
	private String password;
	private String message;
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	/*public String execute(){
		if("test".equals(name)&&"1234".equals(password)){
			this.message = "loginOut successful";
			return "success";
		}
		this.message = "loginOut fail,please check your name or password";
		return "fail";
	}*/
	
}
