package main.coding_170302;

/**这是一个用来显示登录的业务类，其中的用户名和密码都是硬编码
 * Created by peter on 2017/3/3.
 */
public class LoginAction {
    private String name;
    private String password;
    private String message;

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getMessage() {
        return message;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String execute(){
        if("test".equals(name)&&"1234".equals(password)){
            this.message = "login successful";
            return message;
        }
        this.message = "login failed,please check your user/pwd";
        return message;
    }
}
