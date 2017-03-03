package com.pop.practice.homework.first.collection.queue;

import java.io.Serializable;
import java.util.Objects;

import com.pop.practice.homework.first.collection.AbstractCollection;
import com.pop.practice.homework.first.collection.Collection;
import com.pop.practice.homework.first.collection.Iterator;

/**
 * @author haipop Date: 17-2-16 Time: 下午6:34
 */
public class LinkQueue<T> extends AbstractCollection<T> implements Queue<T>, Serializable {

    private static final long serialVersionUID = 7942321140756962637L;

    /**
     * 头节点
     */
    private Node head;

    /**
     * 尾节点,伪节点,这个节点指用来指上一个
     */
    private Node tail;

    /**
     * 现有的元素个数
     */
    private int size;

    /**
     * 遍历器
     */
    private Iterator iterator;

    public LinkQueue() {
        this.size = 0;
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
    public void add(T element) throws IllegalAccessException {
        if (this.head == null) {
            this.head = new Node(element);
            this.tail.setBefore(this.head);
            this.size++;
            return;
        }
        Node node = new Node(element);
        node.setBefore(null);
        node.setNext(this.head.getNext());
        this.head.getNext().setBefore(node);
        this.head = node;
        this.size++;
    }

    @Override
    public void remove(T element) {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        Node tmp = this.head;
        while (tmp.getNext() == null && tmp.getNext() != this.tail) {
            if (Objects.equals(tmp.getData(), element)) {
                Node before = tmp.getBefore();
                Node next = tmp.getNext();
                before.setNext(next);
                next.setBefore(before);
                tmp = null;
                break;
            }
            tmp = tmp.getNext();
        }
    }

    @Override
    public void push(T element) throws IllegalAccessException {
        add(element);
    }

    @Override
    public void push(Collection<T> collection) throws IllegalAccessException {
        addAll(collection);
    }

    @Override
    public int contain(T element) {
        Node tmp = this.head;
        int index = 0;
        while (tmp.next != null && tmp != this.tail) {
            if (Objects.equals(tmp.getData(), element)) {
                return index;
            } else {
                tmp = tmp.next;
                index++;
            }
        }
        return -1;
    }

    @Override
    public T pull() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        Node node = this.tail.getBefore();
        this.tail.setBefore(node.getBefore());
        size--;
        return node.getData();
    }

    @Override
    public Iterator iterator() {
        this.iterator = new LinkQueueIterator();
        return iterator;
    }

    @Override
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override
    @SuppressWarnings("unchecked")
    public T next() {
        return (T) this.iterator.next();
    }

    private class LinkQueueIterator implements Iterator {

        private Node cache;

        LinkQueueIterator() {
            this.cache = head;
        }

        @Override
        public Iterator iterator() {
            return new LinkQueueIterator();
        }

        @Override
        public boolean hasNext() {
            return cache.getNext() == tail;
        }

        @Override
        public T next() {
            return cache.getNext().getData();
        }
    }

    private class Node implements Serializable {

        private static final long serialVersionUID = 6400564182277299061L;

        private T data;

        private Node before;

        private Node next;

        Node(T data) {
            this.data = data;
        }

        T getData() {
            return data;
        }

        void setData(T data) {
            this.data = data;
        }

        Node getBefore() {
            return before;
        }

        void setBefore(Node before) {
            this.before = before;
        }

        Node getNext() {
            return next;
        }

        void setNext(Node next) {
            this.next = next;
        }
    }

}