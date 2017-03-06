package main.java;


import java.util.NoSuchElementException;

/**
 * Created by yrs on 2017/2/23.
 */
public class LinkedList implements List{

    private int size;

    private Node first;

    private Node last;

    public LinkedList() {
        this.first = null;
        this.last =null;
    }

    public void add(Object o) {
        Node l = this.last;
        Node newNode = new Node(l, o, null);
        this.last = newNode;
        if(null == l) {
            this.first = newNode;
        }else {
            l.next = newNode;
        }
        this.size++;
    }

    public void add(int index, Object o) {
        if(index<0 || index>size) {
            throw new IndexOutOfBoundsException();
        }else if(index == this.size) {
            this.add(o);
        }else {
            Node target = targetNode(index);
            Node before = target.prev;
            Node newNode = new Node(before, o, target);
            target.prev = newNode;
            if(null == before) {
                this.first = newNode;
            }else {
                before.next = newNode;
            }
            this.size++;
        }
    }

    public Object get(int index) {
        rangeCheck(index);
        return targetNode(index).data;
    }

    public Object remove(int index) {
        rangeCheck(index);
        Node target = targetNode(index);
        Node before = target.prev;
        Node after = target.next;
        Object o = target.data;

        if(null == before) {
            this.first = null;
        }else {
            before.next = after;
            target.prev = null;
        }

        if(null == after) {
            this.last = before;
        }else {
            after.prev = before;
            target.next = null;
        }
        target.data = null;
        this.size--;

        return o;
    }

    public int size() {
        return this.size;
    }

    public void addFirst(Object o) {
        Node node = first;
        Node newNode = new Node(null, o, node);
        this.first = newNode;
        if(null == node) {
            this.last = newNode;
        }else {
            node.prev = newNode;
        }
        this.size++;
    }

    public void addLast(Object o) {
        add(o);
    }

    public Object removeFirst() {
        Node node = first;
        if (node == null)
            throw new NoSuchElementException();

        first = node.next;
        Object o = node.data;
        if(null == node.next) {
            this.last = null;
        }else {
            first.prev = null;
        }
        node.data = null;
        node.next = null;
        this.size--;
        return o;
    }

    public Object removeLast() {
        Node node = last;
        if (node == null)
            throw new NoSuchElementException();

        last = node.prev;
        Object o = node.data;
        if(null == node.prev) {
            this.first = null;
        }else {
            last.next = null;
        }
        node.data = null;
        node.prev = null;
        this.size--;
        return o;
    }

    public Iterator iterator() {
        return new LinkedListIterator(this);
    }

    private class LinkedListIterator implements Iterator {

        LinkedList linkedList;

        int pos;

        private LinkedListIterator(LinkedList linkedList) {
            this.linkedList = linkedList;
        }

        @Override
        public boolean hasNext() {
            return pos != linkedList.size;
        }

        @Override
        public Object next() {
            if(pos < size) {
                int i = pos;
                pos++;
                return linkedList.get(i);
            }else {
                throw new NoSuchElementException();
            }
        }
    }

    private void rangeCheck(int index) {
        if(index<0 || index>=size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private Node targetNode(int index) {
        //由index值在链表的前半部分还是后半部分，决定是从前向后，还是从后向前查找。
        Node target = new Node();
        if(index < (this.size >> 1)) {
            target = this.first;
            for(int i=0; i<index; i++) {
                target = target.next;
            }
        }else {
            target = this.last;
            for(int i=size-1; i>index; i--) {
                target = target.prev;
            }
        }
        return target;
    }

    private static class Node{
        Object data;
        Node next;
        Node prev;

        Node() {
        }

        Node(Node prev, Object o, Node next) {
            this.data = o;
            this.next = next;
            this.prev = prev;
        }
    }

}
                