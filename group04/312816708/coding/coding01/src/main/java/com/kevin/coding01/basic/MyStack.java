package com.kevin.coding01.basic;

/**
 * 栈 先进后出
 * Created by YinWenBing on 2017/2/25.
 */
public class MyStack<E> {
    private MyArrayList<E> elementData = new MyArrayList<E>();

    /**
     * 往栈中添加元素
     *
     * @param e
     */
    public void push(E e) {
        elementData.add(e);
    }

    /**
     * 删除栈顶元素
     *
     * @return
     */
    public E pop() {
        return elementData.remove(elementData.size() - 1);
    }

    /**
     * 返回栈顶元素
     *
     * @return
     */
    public E peek() {
        return elementData.get(elementData.size() - 1);
    }

    /**
     * 是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return elementData.size() == 0 ? true : false;
    }

    /**
     * 返回栈中元素的数量
     *
     * @return
     */
    public int size() {
        return elementData.size();
    }


    public static void main(String[] args) {
        MyStack<String> myStack = new MyStack<String>();
        myStack.push("A");
        myStack.push("B");
        myStack.push("C");

        System.out.println(myStack.peek());
        System.out.println(myStack.pop());
        System.out.println(myStack.peek());
        System.out.println(myStack.size());
    }
}
