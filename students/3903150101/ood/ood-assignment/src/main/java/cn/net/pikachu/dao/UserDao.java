package cn.net.pikachu.dao;

import cn.net.pikachu.domain.Product;
import cn.net.pikachu.domain.User;

import java.util.*;

/**
 * 
 */
public interface UserDao {

    /**
     * @return
     */
    public List<User> find(Product product);

}