package net.iyouqu.bruceretrofit.util.java;

import java.util.ArrayList;

/**
 * Created by liq on 2017/2/25.
 */

public class CustomStack<E> {

    //重载因子
    private static final float LOAD_FACTOR = 0.75f;
    //需要扩充容量时的大小
    private int resizeCapacity;
    private Object[] data = null;
    //栈容量
    private int capacity;
    //栈顶
    private int top;

    public CustomStack(int initSize) {
        if (initSize >= 0) {
            this.capacity = initSize;
            data = new Object[initSize];
            top = 0;
            this.resizeCapacity = (int) (capacity * LOAD_FACTOR);
        } else {
            throw new RuntimeException("初始化大小不能小于0:" + initSize);
        }
    }

    private ArrayList elementData = new ArrayList();

    public void push(E o){
        checkStackCapacity();
        data[top] = o;
        top++;
    }

    public E pop(){
        if(top<=0)
            throw new RuntimeException("没有元素不能弹出");
        E e = (E) data[top - 1];
        data[top-1] = null;
        --top;
        return e;
    }

    public E peek(){

        return (E) data[top - 1];

    }
    public boolean isEmpty(){
        return top == 0;
    }
    public int size(){
        return top;
    }

    private void checkStackCapacity() {
        if (top == resizeCapacity) {
            capacity = capacity * 2;
            Object[] newData = new Object[capacity];
            System.arraycopy(data, 0, newData, 0, top);
            data = newData;
        }
    }

}
