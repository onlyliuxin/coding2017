package com.coderising.action;

/**
 * Created by zhangwj on 2017/3/9.
 */
public class LoginAction {
    private String Name;
    private String Password;
    private String Message;

    public  void setName(String name)
    {
        this.Name = name;
    }
    public void setPassword(String pass)
    {
        this.Password = pass;
    }

    public String exectue()
    {
        if (this.Name == "test" && this.Password == "1234")
        {
            this.Message = "login successful";
            return "success";
        }
        else
        {
            this.Message = "login failed,please check your user/pwd";
            return "fail";
        }
    }

    public String getMessage()
    {
        return this.Message;
    }



}
