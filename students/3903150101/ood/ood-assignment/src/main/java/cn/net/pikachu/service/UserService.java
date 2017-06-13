package cn.net.pikachu.service;

import cn.net.pikachu.domain.Product;
import cn.net.pikachu.domain.User;

import java.util.*;

/**
 * 
 */
public interface UserService {

    /**
     * @param product 
     * @return
     */
    public List<User> getSubscribers(Product product);

}