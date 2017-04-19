package com.coding.basic;

import java.util.EmptyStackException;

/**
 * A Simple Stack
 */
public class Stack<E> {
    private ArrayList<E> elementData;

    /**
     * 压入栈顶
     *
     * @param e
     */
    public void push(E e) {
        elementData.add(e);
    }

    /**
     * 取出栈顶元素
     *
     * @return
     */
    public E pop() {
        return elementData.remove(elementData.size() - 1);
    }

    /**
     * 查看栈顶元素
     *
     * @return
     */
    public E peek() {
        if(isEmpty()){
          throw new EmptyStackException();
        }
        return elementData.get(elementData.size() - 1);
    }

    /**
     * 栈内是否有元素
     *
     * @return
     */
    public boolean isEmpty() {
        return elementData.size() == 0;
    }

    /**
     * 栈顶内元素个数
     *
     * @return
     */
    public int size() {
        return elementData.size();
    }

    public Stack() {
        elementData = new ArrayList<E>();
    }
}