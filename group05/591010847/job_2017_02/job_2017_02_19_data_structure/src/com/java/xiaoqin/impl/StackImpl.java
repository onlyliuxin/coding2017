package com.java.xiaoqin.impl;

/**
 * Created by xiaoqin on 17-2-26.
 */
public class StackImpl<T> extends ArrayListImpl<T> {

    /**
     * 把数据压住栈
     *
     * @param t
     */
    public void push(T t) {
        add(t);
    }

    /**
     * 返回栈顶数据，并移除出栈
     *
     * @return
     */
    public T pop() {
        if (0 >= size()) {
            throw new NullPointerException("size is 0");
        }
        return remove(size() - 1);
    }

    /**
     * 返回栈顶数据
     *
     * @return
     */
    public T peek() {
        if (0 >= size()) {
            throw new NullPointerException("size is 0");
        }
        return get(size() - 1);
    }

    /**
     * 是否为null
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    /**
     * 大小
     *
     * @return
     */
    @Override
    public int size() {
        return super.size();
    }
}
