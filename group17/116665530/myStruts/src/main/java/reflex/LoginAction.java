package reflex;

public class LoginAction {
    private String name;
    private String passWord;
    private String result;
    private String message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public String exectue(){
        if(this.getName().equals("admin")&&this.getPassWord().equals("123")){
            this.message="登录成功";
            return "sucess";
        }
        this.message="登录失败";
        return "fail";
    }
}
