package org.lite.struts;

/**
 * 杩欐槸涓�釜鐢ㄦ潵灞曠ず鐧诲綍鐨勪笟鍔＄被锛�鍏朵腑鐨勭敤鎴峰悕鍜屽瘑鐮侀兘鏄‖缂栫爜鐨勩�
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
    public String getMessage(){
        return this.message;
    }
}
