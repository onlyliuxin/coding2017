package datastructure.nongeneric;

import datastructure.jdklist.MyLinkedList;

import java.util.List;

/**
 * Created by Haochen on 2017/2/15.
 * TODO:
 */
public class MyQueue {
    //先进先出，进在队尾，出在队头
    private List<Object> list = new MyLinkedList<>();

    public void enQueue(Object o) {
        list.add(o);
    }

    public Object deQueue() {
        Object o = list.get(0);
        list.remove(0);
        return o;
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        for (int i = 0; i < 20; ++i) {
            queue.enQueue(i);
        }
        System.out.println("size: " + queue.size());
        while (!queue.isEmpty()) {
            System.out.print(queue.deQueue() + "[" + queue.size() + "] ");
        }
    }
}
