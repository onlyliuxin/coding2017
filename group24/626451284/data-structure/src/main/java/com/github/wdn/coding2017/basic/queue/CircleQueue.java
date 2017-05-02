package com.github.wdn.coding2017.basic.queue;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/5/2 0002.
 */
public class CircleQueue <E> {
    private final static int DEFAULT_SIZE = 10;
    //用数组来保存循环队列的元素
    private Object[] elementData = new Object[DEFAULT_SIZE] ;
    //队头
    private int front = 0;
    //队尾
    private int rear = 0;
    private int size = 0;

    public static void main(String[] args) {
        CircleQueue<Integer> queue = new CircleQueue<>(9);
        for (int i = 0; i < 5; i++) {
            queue.enQueue(i);
        }
        System.out.println(Arrays.toString(queue.elementData));
        System.out.println("size"+queue.size);
        for (int i = 0; i < 3; i++) {
            if(!queue.isEmpty()){
                System.out.println(queue.deQueue());
            }
        }
        System.out.println(Arrays.toString(queue.elementData));
        System.out.println("size"+queue.size);
        for (int i = 0; i < 5; i++) {
            queue.enQueue(i);
        }
        System.out.println(Arrays.toString(queue.elementData));
        System.out.println("size"+queue.size);
        for (int i = 0; i < 2; i++) {
            if(!queue.isEmpty()){
                System.out.println(queue.deQueue());
            }
        }
        System.out.println(Arrays.toString(queue.elementData));
        System.out.println("size"+queue.size);
        for (int i = 6; i < 9; i++) {
            queue.enQueue(i);
        }
        System.out.println(queue.front);
        System.out.println(queue.rear);
        System.out.println(Arrays.toString(queue.elementData));
        System.out.println("size"+queue.size);
        while (!queue.isEmpty()){
            System.out.print(queue.deQueue());
        }
        System.out.println("");
        System.out.println(queue.front);
        System.out.println(queue.rear);
        System.out.println(queue.isEmpty());
    }
    public CircleQueue(){

    }
    public CircleQueue(int defaultSize){
        elementData = new Object[defaultSize];
    }
    public boolean isEmpty() {
        return size==0;//front==rear && elementData[front]==null;
    }

    public int size() {
        return size;
    }

    public void enQueue(E data) {
        if(size==elementData.length){
            throw new RuntimeException("queue is full");
        }

        elementData[rear] = data;
        size++;
        if(rear==elementData.length-1){
            rear=0;
        }else {
            rear++;
        }
    }

    public E deQueue() {
        if(isEmpty()){
            throw new RuntimeException("queue is empty");
        }
        E result = (E)elementData[front];
        elementData[front]=null;
        size--;
        if(front==elementData.length-1){
            front=0;
        }else {
            front++;
        }
        return result;
    }
}
