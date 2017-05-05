package com.coding.basic;

public class LinkedList implements List {

    private Node head;

    private int size = 0;

    public void add(Object o){
        addLast(o);
    }
    public void add(int index , Object o){

        for (int i = 0; i <= index; i++) {
            head = head.next;
        }
        Node head = this.head;
        Node node = new Node();
        this.head.next = node;
        node.data = o;
        node.next = head.next;
        size++;
    }
    public Object get(int index){
        for (int i = 0; i <= index; i++) {
            head = head.next;
        }
        return head.data;
    }
    public Object remove(int index){
        for (int i = 0; i < index; i++) {
            head = head.next;
        }
        Node head = this.head;
        Object data = head.next.data;
        Node next = this.head.next.next;
        head.next = next;
        return data;
    }

    public int size(){
        return size;
    }

    public void addFirst(Object o){
        Node node = new Node();
        node.next = head;
        node.data = o;
        size++;
    }
    public void addLast(Object o){
        Node node = new Node();
        head.next = node;
        node.data = o;
        node.next = null;
        size++;
    }
    public Object removeFirst(){
        Object data = head.data;
        head.next = null;
        return data;
    }
    public Object removeLast(){
        for (int i = 0; i < size; i++) {
            head = head.next;
        }
        Object data = head.next.data;
        head.next = null;
        return data;
    }
    public Iterator iterator(){
        return null;
    }

    private static  class Node{
        Object data;
        Node next;
    }
}