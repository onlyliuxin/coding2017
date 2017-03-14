package org.korben.list;

import java.util.Objects;

/**
 * Korben's LinkedList
 *
 * Created by Korben on 18/02/2017.
 */
public class KLinkedList<T> implements KList<T> {

    private int size;

    private Node<T> head;

    private Node<T> last;

    public KLinkedList() {
        this.head = new Node<>(null);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(Object o) {
        Node node = this.head;
        while (node.next != null) {
            node = node.next;
            if (Objects.equals(node.data, o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean add(T o) {
        if (this.last == null) {
            this.last = new Node<>(o);
            this.last.pre = this.head;
            this.head.next = this.last;
        } else {
            Node<T> oldLast = this.last;
            this.last = new Node<>(o);
            this.last.pre = oldLast;
            oldLast.next = this.last;
        }
        this.size++;
        return true;
    }

    @Override
    public boolean remove(T o) {
        Node node = this.head;
        while (node.next != null) {
            node = node.next;
            if (Objects.equals(node.data, o)) {
                node.pre.next = node.next;
                if (node.next != null) {
                    node.next.pre = node.pre;
                }
                this.size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        this.head.next = null;
        this.last = null;

        this.size = 0;
    }

    @Override
    public T get(int index) {
        return getNode(index).data;
    }

    @Override
    public T set(int index, T element) {
        Node<T> node = getNode(index);
        node.data = element;
        return element;
    }

    @Override
    public void add(int index, T element) {
        Node<T> node = this.head;
        for (int i = 0; i <= index; i++) {
            node = node.next;
        }
        Node<T> pre = node.pre;
        Node<T> newNode = new Node<>(element);
        pre.next = newNode;
        newNode.pre = pre;
        newNode.next = node;
        node.pre = newNode;

        this.size++;
    }

    @Override
    public T remove(int index) {
        Node<T> node = getNode(index);
        Node<T> pre = node.pre;
        Node<T> next = node.next;
        pre.next = next;
        if (next != null) {
            next.pre = pre;
        }

        this.size--;
        return node.data;
    }

    @Override
    public int indexOf(T o) {
        Node node = this.head;
        int index = 0;
        while (node.next != null) {
            node = node.next;
            if (Objects.equals(node.data, o)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public KIterator<T> iterator() {
        throw new IllegalStateException("方法未实现");
    }

    private Node<T> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> node = this.head;
        for (int i = 0; i <= index; i++) {
            node = node.next;
        }
        return node;
    }

    private static class Node<T> {
        T data;
        Node<T> pre;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }
}
