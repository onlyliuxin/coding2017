package Impl;

import Interface.Iterator;
import Interface.LinkedList;
import ex.MyArrest;

/**
 * Created by Administrator on 2017/2/26.
 */
public class MyLinkedList extends LinkedList {
    private MyLinkedList.Node head;
    private int size = 1;

    public MyLinkedList() {

    }

    private static class Node {
        Object data;
        MyLinkedList.Node next;

        public Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public void add(Object o) {
        if (this.size == 1) {
            head.data = o;
            return;
        }
        MyLinkedList.Node newHead = new MyLinkedList.Node(o, this.head);
        this.size += 1;
        this.head = newHead;
    }


    public void add(int index, Object o) {
        IndexVildate(index);
        int pos = 0;
        if (index == 1) {
            this.head = new Node(o, null);
            return;
        }
        for (MyLinkedList.Node node = this.head; node != null; node = node.next) {
            pos += 1;
            if (pos == index - 1) {
                node.data = o;
                this.size += 1;
            }
        }
    }


    public Object get(int index) {
        int pos = 0;
        for (MyLinkedList.Node node = this.head; node != null; node = node.next) {
            if (pos == index - 1) {
                return node.data;
            }
            pos += 1;
        }
        return null;
    }


    public Object remove(int index) {
        IndexVildate(index);
        int pos = 0;
        MyLinkedList.Node preNode;
        for (MyLinkedList.Node node = this.head; node != null; node = node.next) {
            pos += 1;
            if (pos == index - 2) {
                //record previous node
                preNode = node;
                if (pos == index - 1) {
                    MyLinkedList.Node willDelNode = node;
                    preNode.next = node.next;
                    node = null;
                    this.size -= 1;
                    return willDelNode;
                }
            }
        }
        return null;
    }


    public int size() {
        return this.size;
    }


    public void addFirst(Object o) {
        MyLinkedList.Node newHead = this.head;
        newHead.data = o;
        newHead.next = this.head;
        this.size += 1;
        this.head = newHead;
    }


    public void addLast(Object o) {
        for (MyLinkedList.Node node = this.head; node != null; node = node.next) {
            if (node.next == null) {
                MyLinkedList.Node lastNode = new MyLinkedList.Node(o, null);
                node.next = lastNode;
                this.size += 1;
            }
        }
    }


    public Object removeFirst() {
        MyLinkedList.Node oldHead = this.head;
        this.head = oldHead.next;
        this.size -= 1;
        return oldHead;
    }


    public Object removeLast() {
        for (MyLinkedList.Node node = this.head; node != null; node = node.next) {
            if (node.next == null) {
                MyLinkedList.Node willDelNode = node.next;
                node.next = null;
                this.size -= 1;
                return willDelNode;
            }
        }
        return null;
    }

    public Iterator iterator() {
        return new LinkedListIterator(this);
    }

    private class LinkedListIterator implements Iterator {
        private MyLinkedList linkedList;
        private int index;

        public LinkedListIterator(MyLinkedList linkedList) {
            this.linkedList = linkedList;
            this.index = linkedList.size;
        }

        @Override
        public boolean hasNext() {
            if (index > linkedList.size) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public Object next() {
            Object obj = linkedList.get(index);
            index++;
            return obj;
        }
    }

    private void IndexVildate(int index) {
        if (index > this.size || index < 0) {
            System.out.println("happend error");
        }
    }

    public static void main(String[] args) {
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.add(1, 23);
        MyArrest.arrestEqByBasicType(1, linkedList.size());

    }
}
