package org.apn.coding2017.basic;

/**
 * Created by QiPan on 2017/2/23.
 * 队列，链表实现版本
 */
public class Queue<E> {

    private Node first;
    private Node last;
    private int N;

    private class Node {
        E item;
        Node next;
    }

    public void enQueue(E e) {
        // 向表尾添加元素
        Node oldLast = last;
        last = new Node();
        last.item = e;
        last.next = null;
        if (isEmpty()){// 如果是往空的队列里面添加东西。那么首尾链表都是指向第一个元素
            first = last;
        }else {

            oldLast.next = last;
        }
        N++;
    }

    public E deQueue() {
        E item = first.item;
        first = first.next;
        if (isEmpty()){
            last = null;
        }
        N--;
        return item;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }
}
