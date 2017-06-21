package cn.net.pikachu.srp.dao;

import cn.net.pikachu.srp.domain.Product;

import java.util.*;

/**
 * 
 */
public interface ProductDao {

    /**
     * @return
     */
    public List<Product> find();

}