package com.ifengdzh.code2017.basic;

/**
 * 链表队列:先进先出
 * Created by ajaxfeng on 2017/3/12.
 */
public class LinkedQueue implements Queue {

    private Node head;
    private Node tail;
    private int size=0;

    public class Node {
        public Object data;
        public Node next;
        public Node(Object data, Node next){
            this.data=data;
            this.next=next;
        }
    }

    @Override
    public Object enQueue(Object o) {
        Node node =new Node(o,null);
        if(head==null){
            head= node;
            tail=head;
        }else{
            tail.next= node;
            tail= node;
        }
        size++;
        return o;
    }

    @Override
    public Object deQueue() {
        if(head==null){
            return null;
        }
        Object o=head.data;
        head=head.next;
        size--;
        return o;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }
}
