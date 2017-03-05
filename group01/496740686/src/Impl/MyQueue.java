package Impl;

import Interface.Queue;

/**
 * Created by Administrator on 2017/2/26.
 */
public class MyQueue extends Queue {

    private Node first; // beginning of queue
    private Node last; // end of queue
    private int size; // number of elements on queue

    private static class Node {
        private Object value;
        private Node next;

        public Node(Object value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    public MyQueue() {
        first = null;
        last = null;
        int n = 0;
    }

    @Override
    public void enQueue(Object o) {
        Node oldlast = this.last;
        this.last = new Node(o, null);
        size += 1;
        //第一个进队列
        if (isEmpty()) {
            first = last;
        } else {
            oldlast.next = this.last;
        }

    }

    @Override
    public Object deQueue() {
        if (isEmpty()) {
            return null;
        } else {
            Node oldFirst = this.first;
            Node newFirst = this.first.next;
            this.first = null;
            this.first = newFirst;
            this.size -= 1;
            return oldFirst;

        }
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public int size() {
        return size;
    }
}
