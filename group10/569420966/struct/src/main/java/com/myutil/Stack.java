package com.myutil;

/**
 * 栈
 */
public class Stack<T> {
    private ArrayList<T> elementList = new ArrayList<>();

    /**
     * 入栈
     *
     * @param element 入栈的元素
     */
    public void push(T element) {
        elementList.add(element);
    }

    /**
     * 出栈
     *
     * @return 出栈的元素
     */
    public T pop() {
        if (elementList.size() == 0) {
            throw new ArrayIndexOutOfBoundsException("Stack is empty, don't to pop().");
        }
        T element = elementList.get(elementList.size() - 1);
        elementList.remove(elementList.size() - 1);
        return element;
    }

    /**
     * 获取栈顶元素
     *
     * @return 栈顶元素
     */
    public T peek() {
        if (elementList.size() == 0) {
            throw new ArrayIndexOutOfBoundsException("Stack is empty, don't to peek().");
        }
        return elementList.get(elementList.size() - 1);
    }

    /**
     * 是否为空栈
     *
     * @return true-是 false-否
     */
    public boolean isEmpty() {
        return elementList.size() == 0;
    }

    /**
     * 获取当前栈大小
     *
     * @return 当前栈大小
     */
    public int size() {
        return elementList.size();
    }
}
