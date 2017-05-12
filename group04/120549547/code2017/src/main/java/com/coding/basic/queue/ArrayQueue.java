package com.coding.basic.queue;

import java.util.NoSuchElementException;

/**
 * Created by bobi on 2017/4/1.
 * at code2017
 */
public class ArrayQueue<T> implements Queue<T> {

    private int size;
    private Object[] dataArray;
    private int front;
    private int rear;

    public ArrayQueue(int Capacity) {
        this.dataArray = new Object[Capacity];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }

    @Override
    public boolean add(T t) {
       if (offer(t)){
           return true;
       }else {
           throw new IllegalStateException();
       }
    }

    @Override
    public boolean offer(T t) {
        if (size >= dataArray.length){
            return false;
        }

        dataArray[rear++] = t;

        if (rear == dataArray.length){
            rear = 0;
        }

        size++;
        return true;
    }

    @Override
    public T remove() {
        T value = poll();
        if (value != null){
            return value;
        }else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public T poll() {
        if (size == 0){
            return  null;
        }


        T value = (T) dataArray[front++];
        if (front == dataArray.length){
            front = 0;
        }

        size--;
        return value;

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    @Override
    public T peek() {
        if (size == 0){
            return null;
        }else {
            return (T) dataArray[front];
        }
    }

    public void printf() {
        for (int i = 0; i < size; i++) {

            System.out.print(dataArray[(front + i) % dataArray.length] + " ");
        }
    }

}
