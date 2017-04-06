package com.ace.coding;

import java.util.Objects;

/**
 * Created by ace on 2017/4/2.
 */
public class DLinkedList {
    private Node first;
    private Node last;
    private int currentSize;

    public DLinkedList(){
        currentSize = 0;
        first = null;
        last = null;
    }

    public void add(Object obj){
        Node newNode = new Node();
        newNode.obj = obj;
        if(first == null){
            first = newNode;
            last = newNode;
        } else {

        }
    }


    public static  class Node{
        private Object obj;
        private Node prev;
        private Node next;

        public Node(){}
    }
}
