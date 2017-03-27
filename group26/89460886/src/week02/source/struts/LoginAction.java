package coding.coderising.litestruts;

/**
 * @author jiaxun
 */
public class LoginAction implements Action {

    private String name;
    private String password;
    private String message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String execute() {
        if (!StringUtils.isEmpty(name) && !StringUtils.isEmpty(password) &&
                "test".equals(name) && "1234".equals(password)) {
            message = "login successful";
            return Constants.ACTION_SUCCESS;
        } else {
            message = "login failed,please check your user/pwd";
            return Constants.ACTION_FAILURE;
        }
    }

}
