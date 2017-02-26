package net.iyouqu.bruceretrofit.util.java;

/**
 * Created by liq on 2017/2/25.
 */

public class CustomQueue<E> {

    Object[] data = null;
    //容量
    private int capacity;
    //队尾指针
    private int tail;

    CustomQueue(int initSize) {
        if (initSize >= 0) {
            this.capacity = initSize;
            data = new Object[initSize];
            tail = 0;
        } else {
            throw new RuntimeException("初始化大小不能小于0" + initSize);
        }
    }

    public void enQueue(E o){
        ensureCapacity();
        data[tail] = o;
        tail++;
    }

    public E deQueue(){
        return (E) data[0];
    }

    public boolean isEmpty(){
        return tail == 0;
    }

    public int size(){
        return tail;
    }

    private void ensureCapacity() {
        if (tail == capacity) {
            capacity *= 2;
            Object[] newData = new Object[capacity];
            System.arraycopy(data, 0, newData, 0, tail);
            data = newData;
        }
    }
}
