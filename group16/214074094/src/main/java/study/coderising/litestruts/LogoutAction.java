package study.coderising.litestruts;

/**
 * @Author shane
 * @Time 2017/3/4 12:13
 * @Email stevenchenguang@gmail.com
 * @Desc ...
 */
public class LogoutAction {

    private String name;

    private String message;

    public String execute() {
        if ("test".equalsIgnoreCase(name)) {
            message = name + " logout success";
            return "success";
        }
        message = name + " logout fail";
        return "error";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
