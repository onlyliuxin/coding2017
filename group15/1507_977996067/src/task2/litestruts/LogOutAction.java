package task2.litestruts;

public class LogOutAction {
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