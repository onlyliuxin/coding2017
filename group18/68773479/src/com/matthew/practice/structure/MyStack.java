package com.matthew.practice.structure;

import java.util.EmptyStackException;


public class MyStack {

    public int size(){
        return -1;
    }
    private int[] array;//用数组实现
    private int top; //栈顶指针
    private final static int size = 100;
    public MyStack(){
        array = new int[size];
        top = -1; //栈空的时候
    }
    //压栈
    public void push(int element){
        if(top == size-1){
            throw new StackOverflowError();
        }
        else
            array[++top] = element;
    }
    //弹栈
    public int pop(){
        if(top == -1){
            throw new EmptyStackException();
        }
        return array[top--];
    }
    //判断是否为空
    public boolean isEmpty(){
        return top == -1;
    }
    //返回栈顶元素
    public Integer peek(){
        if(top == -1){
            throw new EmptyStackException();
        }
        return array[top];
    }
}
