package main.coding_170430;

import java.util.EmptyStackException;

/**
 * Created by peterchen on 2017/5/4.
 */
public class CircleQueue <E> {

    private   int DEFAULT_SIZE = 10;

    //用数组来保存循环队列的元素
    private Object[] elementData  ;

    //队头
    private int front = 0;
    //队尾
    private int rear = 0;
    public CircleQueue(){
        elementData =new Object[DEFAULT_SIZE];
    }
    public CircleQueue(int capacity){
        this.DEFAULT_SIZE = capacity;
        elementData = new Object[DEFAULT_SIZE];
    }
    public boolean isEmpty() {
        return front==rear;

    }

    public int size() {
        return (rear-front+DEFAULT_SIZE)%DEFAULT_SIZE;
    }



    public void enQueue(E data) {
        if((rear+1)%DEFAULT_SIZE==front){
            throw new RuntimeException("queue has fulled");
        }
        elementData[rear] = data;
        rear = (rear+1)%DEFAULT_SIZE;
    }

    public E deQueue() {
        if(isEmpty()){
            throw new EmptyStackException();
        }
        E data = (E)elementData[front];
        front = (front+1)%DEFAULT_SIZE;
        return data;
    }
}
