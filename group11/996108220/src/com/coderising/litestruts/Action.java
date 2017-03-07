package com.coderising.litestruts;

public interface Action {

    public String getName();
    public String getPassword();
    public String execute();
    public void setName(String name);
    public void setPassword(String password);
    public String getMessage();
}
