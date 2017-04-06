package secondwork;

public class LoginAction {
	   
	    private String password;
	    private String name;
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

	    public String execute() {
	        if ("test".equals(name) && "1234".equals(password)) {
	            this.message = "login successful";
	            return "success";
	        }
	        this.message = "failed,please check ";
	        return "fail";
	    }

	    public String getMessage() {
	        return this.message;
	    }
	}

