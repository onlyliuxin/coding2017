package com.pop.practice.homework.first.collection.list;

import java.io.Serializable;
import java.util.Objects;

import com.pop.practice.homework.first.collection.AbstractCollection;
import com.pop.practice.homework.first.collection.Iterator;
import com.pop.practice.homework.utils.Math;

/**
 * @author haipop Date: 17-2-16 Time: 下午6:33
 */
public class ArrayList<T> extends AbstractCollection<T> implements List<T>, Serializable {

    private static final long serialVersionUID = -3408657766857424074L;

    /**
     * 阈值
     */
    private static final double THRESHOLD = 0.75F;

    /**
     * 大小
     */
    private int size;

    /**
     * 当前的存储位置
     */
    private int flag;

    /**
     * 元素集合
     */
    private Object[] store;

    /**
     * 是否容量自增
     */
    private boolean autoIncrement;

    /**
     * 遍历器
     */
    private Iterator iterator;

    /**
     * 默认无参
     */
    public ArrayList() {
        this.size = 8;
        this.flag = 0;
        this.autoIncrement = true;
        this.store = new Object[this.size];
    }

    /**
     * 指定大小,不可自增
     */
    public ArrayList(int size) {
        this.size = size;
        this.flag = 0;
        this.autoIncrement = false;
        this.store = new Object[size];
    }

    @Override
    public Iterator iterator() {
        this.iterator = new ArrayListIterator();
        return iterator;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    @SuppressWarnings("unchecked")
    public T next() {
        return (T) iterator.next();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return flag;
    }

    @Override
    public void add(T element) throws IllegalAccessException {
        assessStore(flag + 1, size, store, flag);
        flag++;
        this.store[flag] = element;
    }

    private void assessStore(int left, int size, Object[] store, int flag) throws IllegalAccessException {
        if (!autoIncrement) {
            return;
        }
        double coefficient = Math.div(left, size);
        if (coefficient > THRESHOLD) {
            // 达到阈值,拓展
            Object[] newStore = new Object[this.size * 2];
            System.arraycopy(store, 0, newStore, 0, flag);
            this.store = newStore;
            this.size = size * 2;
        }
    }

    @Override
    public void remove(T element) {
        for (int i = 0; i < flag; i++) {
            if (Objects.equals(this.store[i], element)) {
                System.arraycopy(store, i + 1, store, i, flag - i);
                flag--;
                break;
            }
        }
    }

    @Override
    public int contain(T element) {
        int result = -1;
        for (int i = 0; i < flag; i++) {
            if (Objects.equals(element, store[i])) {
                return i;
            }
        }
        return result;
    }

    @Override
    @SuppressWarnings("uncheckd")
    public T get(int index) throws IndexOutOfBoundsException {
        return (T) this.store[index];
    }

    private class ArrayListIterator implements Iterator {

        private int index;

        ArrayListIterator() {
            index = 0;
        }

        @Override
        public Iterator iterator() {
            return new ArrayListIterator();
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public Object next() {
            return store[index++];
        }
    }
}