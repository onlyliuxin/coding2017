package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.impl.IUserDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.User;

/**
 * Created by Great on 2017/2/7.
 */
public class LoginAction extends ActionSupport {
    private User user;

    @Override
    public String execute() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        IUserDAO userDAO = (IUserDAO) applicationContext.getBean("userDAO");
        if (userDAO.validateUser(user.getUsername(), user.getPassword()) != null) {
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
