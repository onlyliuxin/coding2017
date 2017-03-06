package com.kevin.coding01.basic;

/**
 * 队列：先进先出
 * Created by YinWenBing on 2017/2/25.
 */
public class MyQueue<E> {
    private MyLinkedList<E> elementDate = new MyLinkedList<E>();

    //入队列
    public void enQueue(E e) {
        elementDate.addLast(e);
    }

    //出队列
    public E deQueue() {
        return elementDate.removeFirst();
    }

    public boolean isEmpty() {
        return elementDate.size() == 0 ? true : false;
    }

    public int size() {
        return elementDate.size();
    }

    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue();
        queue.enQueue(1);
        queue.enQueue(2);

        System.out.println(queue.deQueue());//1
    }
}
