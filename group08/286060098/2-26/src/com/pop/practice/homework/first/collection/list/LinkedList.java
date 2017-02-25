package com.pop.practice.homework.first.collection.list;

import java.io.Serializable;
import java.util.Objects;

import com.pop.practice.homework.first.collection.AbstractCollection;
import com.pop.practice.homework.first.collection.Iterator;

/**
 * @author haipop Date: 17-2-16 Time: 下午6:34
 */
public class LinkedList<T> extends AbstractCollection<T> implements List<T>, Serializable {

    private static final long serialVersionUID = 365915684200725970L;

    /**
     * 头节点
     */
    private Node head;

    /**
     * 尾节点
     */
    private Node tail;

    /**
     * 标记位
     */
    private int flag;

    /**
     * 遍历器
     */
    private Iterator iterator;

    public LinkedList() {
        this.head = new Node();
        this.tail = new Node();
        this.flag = 0;
        this.head.setNext(tail);
    }

    @Override
    public boolean isEmpty() {
        return this.flag == 0;
    }

    @Override
    public int size() {
        return this.flag;
    }

    @Override
    public void add(T element) throws IllegalAccessException {
        if (this.head.getData() == null) {
            this.head.setData(element);
            flag++;
            return;
        }
        if (this.tail.getData() == null) {
            this.tail.setData(element);
            this.tail.setBefore(this.head);
            flag++;
            return;
        }
        Node node = new Node(element);
        node.setBefore(this.tail);
        this.tail.next = node;
        this.tail = node;
        flag++;
    }

    @Override
    public void remove(T element) {
        if (this.head == null) {
            return;
        }
        Node tmp = this.head;
        while (tmp.next != null) {
            if (Objects.equals(tmp.getData(), element)) {
                if (tmp == this.head) {
                    if (this.tail.getData() == null) {
                        this.head.setData(null);
                    } else {
                        this.head = this.head.next;
                        this.head.before = null;
                    }
                    flag--;
                    return;
                }
                Node next = tmp.next;
                Node before = tmp.before;
                if (next == null) {
                    before.setNext(null);
                } else {
                    before.setNext(next);
                }
                next.setBefore(before);
                flag--;
                tmp = null;
            }
            tmp = tmp.next;
        }
    }

    @Override
    public int contain(T element) {
        Node tmp = this.head;
        int index = 0;
        while (tmp.next != null) {
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
    public T get(int index) throws IndexOutOfBoundsException {
        Node tmp = this.head;
        for (int i = 1; i < index; i++) {
            if (tail == tmp) {
                // 到最后还没到指定位置,越界
                throw new IndexOutOfBoundsException();
            }
            tmp = tmp.getNext();
        }
        return tmp.getData();
    }

    @Override
    public Iterator iterator() {
        this.iterator = new LinkListIterator();
        return iterator;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    @SuppressWarnings("unchecked")
    public T next() {
        return (T) iterator.next();
    }

    private class LinkListIterator implements Iterator {

        private Node cache;

        LinkListIterator() {
            cache = head;
        }

        @Override
        public Iterator iterator() {
            return new LinkListIterator();
        }

        @Override
        public boolean hasNext() {
            return cache.getNext() == null;
        }

        @Override
        public Object next() {
            return cache.getNext();
        }
    }

    /**
     * 链表节点类 --> 这里做了双向链表，这里如果是一个跳表的话查询性能会更好一点
     */
    private class Node implements Serializable {

        private static final long serialVersionUID = 6327349429030778592L;
        /**
         * 数据
         */
        private T data;

        /**
         * 下一节点
         */
        private Node before;

        /**
         * 下一节点
         */
        private Node next;

        Node() {
        }

        Node(T data) {
            this.data = data;
            this.next = null;
        }

        Node(T data, Node before) {
            this.data = data;
            this.before = before;
        }

        Node(T data, Node before, Node next) {
            this.data = data;
            this.before = before;
            this.next = next;
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