package com.coding.basic.queue;

/**
 * Created by 14258 on 2017/5/6.
 */
public class LoopQueue {


    private int capacity=0;
    private int begin = 0;
    private int end = 0;

    private Object[] queue;



    public LoopQueue(int initSize) {
        queue = new Object[initSize];
    }


    public boolean isEmpty() {
        return begin == end;
    }


    public boolean isFull() {
        return capacity == queue.length;
    }


    public int size() {
        return  capacity;
    }

    public void push(Object o) {
        if (isFull()) {
            throw new RuntimeException("The queue is full");
        }
        queue[end++] = o;
        capacity++;
    }

    public boolean pop() {
        if (isEmpty()) {
            throw new RuntimeException("The queue is empty");
        }

        Object oldValue = queue[begin];
        queue[begin] = null;
        begin = (begin + 1) % queue.length;
        capacity--;
        return true;
    }

    public Object front() {
        if (isEmpty()) {
            return false;
        }
        return queue[begin];
    }

    public static void main(String[] args) {
        LoopQueue loopQueue = new LoopQueue(6);

        loopQueue.push("1");
        loopQueue.push("2");
        loopQueue.push("3");
        loopQueue.push("4");
        loopQueue.push("5");
        loopQueue.push("6");

        System.out.println(loopQueue.size());
        System.out.println(loopQueue.isEmpty());
        System.out.println(loopQueue.isFull());

        loopQueue.pop();
        loopQueue.pop();
        loopQueue.pop();
        loopQueue.pop();
        loopQueue.pop();
        loopQueue.pop();
        loopQueue.pop();

        System.out.println(loopQueue.size());
        System.out.println(loopQueue.isEmpty());
        System.out.println(loopQueue.isFull());



    }





}
