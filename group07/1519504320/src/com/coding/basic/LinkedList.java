package com.coding.basic;

public class LinkedList implements List {

    private Node head;

    public void add(Object o) {
        Node n = head.next;
        while (n.next != null) {
            n = n.next;
        }
        Node n1 = new Node();
        n1.data = o;
        n.next = n1;
    }

    public void add(int index, Object o) {
        if (index == 0) {
            Object o1 = head.data;
            head.data = o;
            Node next = new Node();
            next.data = o1;
            head.next = next;
        }
        if (index < 0) {
            return;
        }
        if (size() - 1 < index) {
            return;
        } else {
            int flag = 0;
            Node pre = head;
            while (flag != index - 1) {
                pre = pre.next;
                flag++;
            }
            Node current = new Node();
            current.data = o;
            Node next = pre.next;
            pre.next = current;
            current.next = next;

        }


    }

    public Object get(int index) {
        if (index < 0) {
            return null;
        }
        if (size() - 1 < index) {
            return null;
        } else {
            int flag = 0;
            Node n = head;
            while (flag != index) {
                n = n.next;
                flag++;
            }
            return n.data;
        }
    }

    public Object remove(int index) {
        if (index < 0) {
            return null;
        }
        if (size() - 1 < index) {
            return null;
        } else {
            int flag = 0;
            Node pre = head;
            while (flag != index - 1) {
                pre = pre.next;
                flag++;
            }
            Node current = pre.next;
            Object c = current.data;
            pre.next = current.next;
            return c;
        }
    }

    public int size() {
        int size = -1;
        if (head.data != null) {
            size = 0;
            Node n = head.next;
            while (n.next != null) {
                size++;
                n = n.next;
            }
        }
        return size;
    }

    public void addFirst(Object o) {
        if (head.data != null) {
            Node n = new Node();
            n.data = o;
            n.next = head;
        } else {
            head.data = o;
        }

    }

    public void addLast(Object o) {
        if (head.data == null) {
            head.data = o;
        } else {
            Node n = head.next;
            while (n.next != null) {
                n = n.next;
            }
            n.next.data = o;
        }
    }

    public Object removeFirst() {
        if (head.data == null) {
            return null;
        } else {
            Object o = head.data;
            head = head.next;
            return o;
        }
    }

    public Object removeLast() {
        if (head.data == null) {
            return null;
        } else {
            Node n = head.next;
            while (n.next != null) {
                n = n.next;
            }
            Object o = n.data;
            n.data = null;
            return o;
        }
    }

    public Iterator iterator() {
        return null;
    }


    private static class Node {
        Object data;
        Node next;

    }


}
