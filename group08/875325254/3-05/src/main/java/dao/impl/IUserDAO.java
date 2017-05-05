package dao.impl;

import pojo.User;

/**
 * Created by Great on 2017/2/7.
 */
public interface IUserDAO {
    public User validateUser(String username, String password);
}
