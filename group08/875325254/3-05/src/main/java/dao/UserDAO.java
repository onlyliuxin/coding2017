package dao;

import dao.impl.IUserDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import pojo.User;

import java.util.List;

/**
 * Created by Great on 2017/2/7.
 */
public class UserDAO extends BaseDAO implements IUserDAO {
    public User validateUser(String username, String password) {
        String hql = "from User u where u.username = ? and u.password = ?";
        Session session = getSession();
        Query query = session.createQuery(hql);
        query.setParameter(0, username);
        query.setParameter(1, password);
        List users = query.list();
        session.close();
        if (users.size() != 0) {
            return (User) users.get(0);
        }
        return null;
    }
}
