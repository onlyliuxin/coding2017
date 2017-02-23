package datastructure.nongeneric;

import datastructure.jdklist.MyLinkedList;

import java.util.List;

/**
 * Created by Haochen on 2017/2/15.
 * TODO:
 */
public class MyStack {
    //后进先出，进出都在队头
    private List<Object> list = new MyLinkedList<>();

    public void push(Object o) {
        list.add(0, o);
    }

    public Object pop() {
        Object o = list.get(0);
        list.remove(0);
        return o;
    }

    public Object peek() {
        return list.get(0);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public static void main(String[] args) {
        MyStack stack = new MyStack();
        for (int i = 0; i < 20; ++i) {
            stack.push(i);
        }
        System.out.println("size: " + stack.size());
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + "[" + stack.size() + "] ");
        }
    }
}
