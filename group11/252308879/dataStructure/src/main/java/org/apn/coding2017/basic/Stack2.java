package org.apn.coding2017.basic;

/**
 * Created by Pan on 2017/2/25.
 * 栈（链表实现）： 下压栈。操作栈顶元素
 */
public class Stack2<E> {

    private Node first;
    private int N;

    private class Node {
        E item;
        Node next;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public int size(){
        return  N;
    }

    /**
     * 向栈顶添加元素
     * @param element
     */
    public void push (E element){
        Node oldFirst = first;
        first = new Node();
        first.item = element;
        first.next = oldFirst;
        N++;
    }

    /**
     * 弹出栈顶元素
     * @return
     */
    public E pop(){
        E item = first.item;
        first = first.next;
        N--;
        return item;
    }
}
