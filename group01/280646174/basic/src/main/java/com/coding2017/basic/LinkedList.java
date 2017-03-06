package com.coding2017.basic;

public class LinkedList implements List {

    private Node head;

    private Node tail;

    private int size;

    public void add(Object o) {
        addLast(o);
    }

    public void add(int index, Object o) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == size) {
            addLast(o);
        } else if (index == 0) {
            addFirst(o);
        } else {
            Node node = new Node(o);
            Node prevNode = getNode(index - 1);
            Node nextNode = prevNode.next;
            prevNode.next = node;
            node.prev = prevNode;
            nextNode.prev = node;
            node.next = nextNode;
            size++;
        }
    }

    private Node getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node node = head;
        for (int j = 0; j < index; j++) {
            node = node.next;
        }
        return node;
    }

    public Object get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return getNode(index).data;
    }

    public Object remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        } else {
            Node node = getNode(index);
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
            return node.data;
        }
    }

    public int size() {
        return size;
    }

    public void addFirst(Object o) {
        Node node = new Node(o);
        if (size == 0) {
            head = node;
            tail = node;
        } else {
            head.prev = node;
            node.next = head;
            head = node;
        }
        size++;
    }

    public void addLast(Object o) {
        if (size == 0) {
            addFirst(o);
        } else {
            Node node = new Node(o);
            tail.next = node;
            node.prev = tail;
            tail = node;
            size++;
        }
    }

    public Object removeFirst() {
        if (size == 0) {
            throw new IndexOutOfBoundsException();
        }
        Node node = head;
        if (size == 1) {
            head = null;
            tail = null;
            size--;
        } else {
            head.next.prev = null;
            head = head.next;
            size--;
        }
        return node.data;
    }

    public Object removeLast() {
        if (size == 0) {
            throw new IndexOutOfBoundsException();
        }
        if (size == 1) {
            return removeFirst();
        }
        Node node = tail;
        tail.prev.next = null;
        tail = tail.prev;
        size--;
        return node.data;
    }

    public Iterator iterator() {
        return new LinkedListIterator();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        if (size > 0) {
            builder.append(get(0));
        }
        for(Node node = head.next; node != null; node = node.next) {
            builder.append(", ").append(node.data);
        }
        builder.append("]");
        return builder.toString();
    }

    private static class Node {
        private Object data;
        private Node next;
		private Node prev;

		public Node() {}

        private Node(Object data) {
		    this.data = data;
        }
    }

    private class LinkedListIterator implements Iterator {
        private Node node;

        public LinkedListIterator() {
            this.node = LinkedList.this.head;
        }

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public Object next() {
            Node temp = node;
            node = node.next;
            return temp.data;
        }
    }
}
