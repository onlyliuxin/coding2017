package com.github.miniyk2012.coding2017.coderising.litestruts;

/**
 * 这是一个用来展示登录的业务类， 其中的用户名和密码都是硬编码的。
 * @author liuxin
 *
 */
public class LogoutAction{
    private String name ;
    private String password;
    private String message;

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
    
    public void setMessage(String message) {
    	this.message = message;
    }

    @Override
	public String toString() {
		return "LogoutAction [name=" + name + ", password=" + password + ", message=" + message + "]";
	}

	public String execute(){
            if("test".equals(name) && "1234".equals(password)){
                this.message = "logout successful";
                return "success";
            }
            this.message = "logout failed,please check your user/pwd";
            return "error";
    }

    public void setName(String name){
        this.name = name;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getMessage(){
        return this.message;
    }
}
