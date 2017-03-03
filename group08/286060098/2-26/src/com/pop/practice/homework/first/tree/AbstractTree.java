package com.pop.practice.homework.first.tree;

import com.pop.practice.homework.first.collection.Iterator;
import com.pop.practice.homework.first.collection.list.List;

/**
 * @author haipop Date: 17-2-20 Time: 上午9:53
 */
public abstract class AbstractTree<T extends Comparable> implements Tree<T> {

    @Override
    public void addNode(T[] elements) throws IllegalAccessException {
        for (T ele : elements) {
            addNode(ele);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void addNode(List<T> elements) throws IllegalAccessException {
        Iterator iterator = elements.iterator();
        while (iterator.hasNext()) {
            addNode((T) iterator.next());
        }
    }


    protected abstract void addNode(T elements) throws IllegalAccessException;
}