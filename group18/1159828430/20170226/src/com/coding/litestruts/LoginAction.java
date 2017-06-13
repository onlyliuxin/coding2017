package com.coding.litestruts;
/**
 * @author Scholar
 * @Time：2017年2月27日 下午8:50:23
 * @version 1.0
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
    public String getMessage(){
        return this.message;
    }
}