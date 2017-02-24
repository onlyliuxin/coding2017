package org.apn.coding2017.basic;

/**
 * Created by QiPan on 2017/2/23.
 */
public class LinkedList implements List {

    Node last;


    public boolean add(Object o) {

        return true;
    }

    public Object set(int index, Object element) {
        return null;
    }

    public boolean add(int index, Object o) {

        return true;
    }

    public Object get(int index) {
        return null;
    }

    public Object remove(int index) {
        return null;
    }

    public int size() {
        return 0;
    }

    public boolean isEmpty() {
        return false;
    }

    public Iterator iterator() {
        return null;
    }
    
    private static class Node {
        Object item;
        Node next;

        Node(Object item, Node next) {
            this.item = item;
            this.next = next;
        }
        public Object getItem() {
            return item;
        }

        public void setItem(Object item) {
            this.item = item;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

}
