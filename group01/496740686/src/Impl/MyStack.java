package Impl;

import Interface.ArrayList;
import Interface.Stack;


/**
 * Created by Administrator on 2017/2/26.
 */
public class MyStack extends Stack {

    private MyStack.Node first; // beginning of queue
    private MyStack.Node last; // end of queue
    private int size; // number of elements on queue

    private static class Node {
        private Object value;
        private MyStack.Node next;

        public Node(Object value, MyStack.Node next) {
            this.value = value;
            this.next = next;
        }
    }

    public MyStack() {
        first = null;
        last = null;
        int n = 0;
    }

    @Override
    public void push(Object o) {
        if (isEmpty()) {
            MyStack.Node oldFirst = this.first;
            this.first = new MyStack.Node(o, null);
            size += 1;
            //第一个进栈
            if (isEmpty()) {
                first = last;
            } else {
                oldFirst.next = this.last;
            }
        }
    }

    @Override
    public Object pop() {
        if (isEmpty()) {
            return null;
        } else {
            MyStack.Node oldFirst = this.first;
            this.first = oldFirst.next;
            this.size -= 1;
            return oldFirst;

        }

    }

    @Override
    public Object peek() {
        return this.first;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }
}
