package net.coding.coderising.litestruts;

/**
 * Created by xiaoyuan on 02/03/2017.
 */
public class LogoutAction {

    String ifLogout;
    String message;

    public String execute() {
        if (ifLogout.equals("yes")) {
            this.message = "success";
            return "success";
        } else {
            this.message = "error";
            return "error";
        }
    }

    public String getIfLogout() {
        return ifLogout;
    }

    public void setIfLogout(String ifLogout) {
        this.ifLogout = ifLogout;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
