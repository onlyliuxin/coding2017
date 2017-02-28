package com.pop.practice.homework.first.collection.stack;

import java.io.Serializable;

import com.pop.practice.homework.first.collection.AbstractCollection;
import com.pop.practice.homework.first.collection.Iterator;
import com.pop.practice.homework.first.collection.list.LinkedList;
import com.pop.practice.homework.first.collection.list.List;

/**
 * @author haipop Date: 17-2-16 Time: 下午6:33
 */
public class LinkStack<T> extends AbstractCollection<T> implements Stack<T>, Serializable {

    private static final long serialVersionUID = -2813631170103864318L;

    /**
     * 数据存储
     */
    private List<T> cache;

    /**
     * 数据量
     */
    private int size;

    /**
     * 遍历器
     */
    private Iterator iterator;

    public LinkStack() {
        this.size = 0;
        this.cache = new LinkedList<T>();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void push(T element) throws IllegalAccessException {
        this.cache.add(element);
        size++;
    }

    @Override
    public void add(T element) throws IllegalAccessException {
        this.cache.add(element);
        size++;
    }

    @Override
    public T pull() throws IndexOutOfBoundsException {
        T result = this.cache.get(size - 1);
        size--;
        return result;
    }

    @Override
    public void remove(T element) {
        cache.remove(element);
        size--;
    }

    @Override
    public int contain(T element) {
        return cache.contain(element);
    }

    @Override
    public Iterator iterator() {
        this.iterator = this.cache.iterator();
        return iterator;
    }

    @Override
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override
    @SuppressWarnings("unchecked")
    public T next() {
        return (T) this.iterator.next();
    }

}