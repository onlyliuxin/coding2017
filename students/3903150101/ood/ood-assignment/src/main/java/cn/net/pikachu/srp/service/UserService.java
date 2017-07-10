package cn.net.pikachu.srp.service;

import cn.net.pikachu.srp.domain.Product;
import cn.net.pikachu.srp.domain.User;

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