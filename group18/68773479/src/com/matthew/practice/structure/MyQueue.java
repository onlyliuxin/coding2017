package com.matthew.practice.structure;


public interface MyQueue<T> {
    /**
     * 入队：从队尾加入一个元素
     * @param t
     * */
    void add(T t);
    /**
     * 出队：移走队头元素并返回
     * @return 当前队头元素*/
    T remove();
    /**
     * 当前队列的元素个数*/
    int size();
    /**
     * 判断当前队列是否为空
     * @return */
    boolean isEmpty();
    /**
     * 只是返回队头元素，并未删掉
     * @return t*/
    T front();
}
