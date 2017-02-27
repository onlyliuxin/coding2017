package com.coding.basic;

public class LinkedList implements List {

    private Node head;
    private int size = 0;
    private Iterator iterator = new LinkedListIterator();


    public void add(Object o) {
        Node newNode = new Node(o, null);
        if (head == null) {
            head = newNode;
        } else {
            node(size - 1).next = newNode;
        }
        size++;
    }

    public void add(int index, Object o) {
        checkForAdd(index);
        if (index == size) {
            add(o);
        }else {
            Node newNode = new Node(o, null);
            if (index == 0){
              addFirst(o);
            } else {
                Node preNode = node(index - 1);
                Node now = preNode.next;
                preNode.next = newNode;
                newNode.next = now;
                size++;
            }
        }

    }

    private Node node(int index) {
        Node x = head;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x;
    }

    public Object get(int index) {
        checkIndex(index);
        return node(index).data;
    }

    /**
     * 让被删除的引用的持有者指向下一个节点
     * @param index
     * @return
     */
    public Object remove(int index) {
        final Object ret;
        checkIndex(index);
        if (index == 0) {
            Node removeNode = head;
            ret = head.data;
            head = removeNode.next;
        } else {
            Node pre = node(index - 1);
            Node removeNode = pre.next;
            ret = removeNode.data;
            pre.next = removeNode.next;
        }
        size--;
        return ret;
    }

    public int size() {
        return size;
    }

    public void addFirst(Object o) {
        head = new Node(o, head);;
        size++;
    }

    public void addLast(Object o) {
        add(o);
    }

    public Object removeFirst() {
        if (size == 0){
            return null;
        }else {
            return remove(0);
        }
    }

    public Object removeLast() {
        return remove(size - 1);
    }

    public Iterator iterator() {
        return iterator;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(String.format("index=%s, size=%s", index, size));
        }
    }

    private void checkForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException(String.format("index=%s, size=%s", index, size));
        }
    }

    private static class Node {
        Object data;
        Node next;

        public Node() {
        }

        public Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private class LinkedListIterator implements Iterator{

        private Node next;

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public Object next() {
            if (next == null) {
                throw new IndexOutOfBoundsException("there is no node in list");
            }
            Node ret = next;
            next = next.next;
            return ret.data;
        }
    }
}
