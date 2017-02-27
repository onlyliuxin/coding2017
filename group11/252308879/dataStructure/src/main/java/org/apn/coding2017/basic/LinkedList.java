package org.apn.coding2017.basic;

/**
 * Created by QiPan on 2017/2/23.
 */
public class LinkedList {

    private Node head;
    private int size;
    public LinkedList() {
        head = new Node(null, null);
        size = 0;
    }

    public void add(Object o){
        add(size, o);
    }

    public void add(int index , Object o){
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
        Node frontNode = getNode(index-1);
        Node newNode = new Node(o, frontNode.next);
        frontNode.next = newNode;
        size++;

    }

    private Node getNode(int index) {
        Node node = head;
        int i = 0;
        while(node.next != null && i <= index) {
            node = node.next;
            i++;
        }
        return node;
    }

    public Object get(int index){
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }

        Node node = getNode(index);
        return node.data;
    }

    public Object remove(int index){
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
        Node frontNode = getNode(index-1);
        Node oldNode = getNode(index);
        frontNode.next = oldNode.next;
        size--;
        return oldNode.data;
    }

    public int size(){
        return size;
    }

    public void addFirst(Object o){
        add(0, o);
    }

    public void addLast(Object o){
        add(size, o);
    }

    public Object removeFirst(){
        return remove(0);
    }

    public Object removeLast(){
        return remove(size-1);
    }

    public Iterator iterator(){
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator {
        int index;
        final int capacity = size;
        LinkedListIterator() {
            index = 0;
        }
        @Override
        public boolean hasNext() {
            return index < capacity;
        }

        @Override
        public Object next() {
            return get(index++);
        }

        @Override
        public void remove() {

        }
    }

    private String outOfBoundsMsg(int index) {
        return "index:" + index + ", size:" + size;
    }

    private static  class Node {
        Object data;
        Node next;

        Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }
    }


}
