package com.ifengdzh.code2017.basic;

/**
 * 链表类型List
 * Created by ajaxfeng on 2017/3/12.
 */
public class LinkedList implements List {

    private int size = 0;
    private Node head = null;
    private Node tail = null;

    /**
     * 基础数据
     */
    public static class Node {
        public Object data;
        public Node next;

        public Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Node(Object data) {
            this(data, null);
        }
    }

    @Override
    public Object add(Object o) {
        Node node = new Node(o);
        if (head == null) {
            head = node;
        }
        if(tail==null){
            tail=head;
        }else{
            tail.next = node;
            tail = node;
        }
        size++;
        return o;
    }

    @Override
    public Object add(int index, Object o) {
        Node thisNode = new Node(o);
        if(index==0){
            thisNode.next=head;
            head= thisNode;
            size++;
            return o;
        }

        while(index > size) {
             add(null);
        }
        if(index==size){
            return add(o);
        }
        if (index < size) {
            //当前
            Node node = getNode(index-1);
            Node next = node.next;
            node.next = thisNode;
            thisNode.next = next;
            size++;
        }
        return o;
    }

    /**
     * 找到当前节点
     *
     * @param index
     * @return
     */
    private Node getNode(int index) {
        if (index < 0 || index > size) {
            throw new RuntimeException("index invalid ..." + index);
        }
        Node currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    @Override
    public Object remove(int index) {
        if (index < 0 || index >= size) {
            throw new RuntimeException("index invalid ..." + index);
        }
        Node thisNode = null;

        if (index == 0) {
            thisNode = head;
            head = head.next;
        } else{
            //找到节点前一个节点
            Node frontNode = getNode(index - 1);
            thisNode = frontNode.next;
            Node nextNode = thisNode.next;

            frontNode.next = nextNode;
            //最后一个
            if (tail.equals(thisNode)) {
                tail = frontNode;
            }
        }
        size--;
        return thisNode.data;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object get(int index) {
        return getNode(index).data;
    }

    @Override
    public Iterator iterator() {
        return new ListIterator(this);
    }
}
