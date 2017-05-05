package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Created by Great on 2017/2/13.
 */
public class BaseDAO {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        Session session = sessionFactory.openSession();
        return session;
    }
}
