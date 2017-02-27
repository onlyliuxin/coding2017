package com.pop.practice.homework.first.tree;

import com.pop.practice.homework.first.collection.list.List;

/**
 * @author haipop Date: 17-2-19 Time: 下午5:12
 */
public interface Tree<T extends Comparable> {

    /**
     * 添加节点
     */
    void addNode(T... elements) throws IllegalAccessException;

    /**
     * 添加节点
     */
    void addNode(List<T> elements) throws IllegalAccessException;

    /**
     * 删除节点
     */
    void removeNode(T element) throws IllegalAccessException;

    /**
     * 节点查找,找到返回节点信息,找不到返回null
     */
    boolean contain(T data) throws IllegalAccessException;

}