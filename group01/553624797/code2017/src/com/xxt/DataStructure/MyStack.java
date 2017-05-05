package com.xxt.DataStructure;

import java.util.EmptyStackException;

/**
 * Created by star on 2017/2/26.
 */
public class MyStack {


    //采用数组实现；
    private Object[] array;
    //栈顶指针
    private int top;
    private final static int size = 100;

    public MyStack(Object[] array, int top) {
        this.array = array;
        //空栈
        top = -1 ;
    }

    public void push(Object elementData){
        //栈满
        if(top == size - 1){
            throw new StackOverflowError();
        }else {
            array[++top] = elementData;
        }
    }

    //弹栈
    public Object pop(){
        if( top == -1){
            throw  new EmptyStackException();
        }else {
            return array[top--];
        }
    }

    public boolean isEmpty(){
        return top == -1;
    }

    public  Object peek(){
        if(top == -1){
            throw new EmptyStackException();
        }else {
            return array[top];
        }
    }
}
