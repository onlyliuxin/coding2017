package cn.net.pikachu.dao;

import cn.net.pikachu.domain.Product;

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