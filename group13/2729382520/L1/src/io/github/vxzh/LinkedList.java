package io.github.vxzh;

/**
 * Created by vxzh on 23/02/2017.
 */
public class LinkedList implements List {

    private int size = 0;

    private Node head;
    private Node tail;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void linkLast(Object o) {
        Node node = tail;
        Node newNode = new Node(tail, o, null);
        tail = newNode;
        if (node == null)
            head = newNode;
        else
            node.next = newNode;
        size++;
    }

    private void linkBefore(Object o, Node node) {
        Node prev = node.prev;
        Node newNode = new Node(prev, o, node);
        node.prev = newNode;
        if (prev == null)
            head = newNode;
        else
            prev.next = newNode;
        size++;
    }

    public boolean add(Object o) {
        linkLast(o);
        return true;
    }

    public boolean add(int index, Object o) {
        if (index > size || index < 0)
            throw new RuntimeException("IndexOutOfBoundsException");
        if (index == size)
            linkLast(o);
        else
            linkBefore(o, node(index));
        return true;
    }

    public boolean remove(Object o) {
        if (o == null) {
            return false;
        } else {
            for (Node x = head; x != null; x = x.next) {
                if (o.equals(x.data)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean remove(int index) {
        if (index >= size || index < 0)
            throw new RuntimeException("IndexOutOfBoundsException");
        unlink(node(index));
        return true;
    }

    public Object get(int index) {
        if (index >= size || index < 0)
            throw new RuntimeException("IndexOutOfBoundsException");
        return node(index).data;
    }

    private void unlink(Node x) {
        Node prev = x.prev;
        Node next = x.next;

        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.data = null;
        size--;
    }

    private Node node(int index) {
        if (index < (size >> 1)) {
            Node x = head;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node x = tail;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }


    private static class Node {
        Object data;
        Node prev;
        Node next;

        Node(Node prev, Object element, Node next) {
            this.data = element;
            this.next = next;
            this.prev = prev;
        }

    }

}



