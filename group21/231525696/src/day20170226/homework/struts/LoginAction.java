package day20170226.homework.struts;

public class LoginAction {
	private String name ;
    private String password;
    private String message;

    public String execute(){
        if("test".equals(name) && "1234".equals(password)){
            this.message = "login successful";
            return "success";
        }
        this.message = "login failed,please check your user/pwd";
        return "fail";
    }

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

}
