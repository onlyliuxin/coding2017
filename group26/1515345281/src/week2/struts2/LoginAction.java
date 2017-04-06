package week2.struts2;

public class LoginAction {
	private String userName;
	private String password;
	private String message;

	
	public String excute(){
		if("沈健".equals(userName) && "123456".equals(password)){
			this.message="login successful";
			return "success";
		}
		
		this.message="login failed,please check your user/pwd";
		return "fail";
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

}
