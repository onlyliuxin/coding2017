package cn.net.pikachu.domain;

import java.util.*;

/**
 * 产品
 */
public class Product {

    /**
     * Default constructor
     */
    public Product() {
    }

    /**
     * 产品id
     */
    private String id;

    /**
     * 产品名称
     */
    private String name;

    /**
     * 产品介绍
     */
    private String des;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}