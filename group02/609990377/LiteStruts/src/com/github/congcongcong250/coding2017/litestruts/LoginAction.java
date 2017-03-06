package com.github.congcongcong250.coding2017.litestruts;

/**
 * 这是一个用来展示登录的业务类， 其中的用户名和密码都是硬编码的。
 * @author liuxin
 *
 */
public class LoginAction{
    private String name ;
    private String password;
    private String message;

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String execute(){
            if("test".equals(name) && "1234".equals(password)){
                this.message = "login successful";
                return "success";
            }
            this.message = "login failed,please check your user/pwd";
            return "fail";
    }

    public void setName(String name){
        this.name = name;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setMessage(String message){
    	this.message = message;
    }
    public String getMessage(){
        return this.message;
    }
}
