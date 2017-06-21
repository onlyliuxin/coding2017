package cn.net.pikachu.srp.dao;

import cn.net.pikachu.srp.domain.Product;
import cn.net.pikachu.srp.domain.User;

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