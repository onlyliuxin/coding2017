package dataStruct.com.coding.basic;

import java.util.NoSuchElementException;

/**
 * Created by songbao.yang on 2017/2/22.
 *
 */
public class Queue {

    private Object[] elementData;
    private int head; //对头的位置
    private int tail; //队尾的位置
    private int size; //队列中元素的个数
    private static final int MIN_INITIAL_CAPACITY = 10;

    public Queue() {
        this.elementData = new Object[MIN_INITIAL_CAPACITY];
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }

    public Queue(int initCapcacity) {
        if (initCapcacity < MIN_INITIAL_CAPACITY){
            initCapcacity = MIN_INITIAL_CAPACITY;
        }
        this.elementData = new Object[initCapcacity];
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }

    public void enQueue(Object o){
        ensureCapacity(size+1);
        if(size != 0){
            tail++;
        }
        if(tail == elementData.length){
            tail = 0;
        }
        elementData[tail] = o;
        size++;
    }

    private void ensureCapacity(int minCapcacity){
        if(minCapcacity <= elementData.length){
            return;
        }

        int newCapcacity = elementData.length << 1;
        if (newCapcacity < elementData.length){
            newCapcacity = Integer.MAX_VALUE;
        }

        Object[] newData = new Object[newCapcacity];
        if(size != 0){
            if(tail >= head){
                System.arraycopy(elementData, head, newData, 0, size);
            } else {
                System.arraycopy(elementData, head, newData, 0, elementData.length - head);
                System.arraycopy(elementData, 0, newData, elementData.length - head, tail + 1);
            }
            elementData = newData;
            head = 0;
            tail = this.size - 1;
        }
    }

    public Object deQueue(){
        if (isEmpty()){
            throw new NoSuchElementException("empty queue");
        }
        Object ele = elementData[head];
        size--;
        head++;
        if(head == elementData.length){
            head = 0;
        }
        return ele;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }
}
