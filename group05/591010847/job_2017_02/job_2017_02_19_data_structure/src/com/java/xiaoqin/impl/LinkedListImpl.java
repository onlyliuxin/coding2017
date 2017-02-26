package com.java.xiaoqin.impl;

import com.java.xiaoqin.interfaces.IIterator;
import com.java.xiaoqin.interfaces.IList;
import com.java.xiaoqin.interfaces.IQueue;

/**
 * Created by xiaoqin on 17-2-26.
 */
public class LinkedListImpl<T> implements IList<T>, IQueue<T> {

    private Node<T> head;
    private Node<T> last;
    private int size = 0;

    public LinkedListImpl() {
    }

    @Override
    public void add(T t) {
        if (size == 0) {
            head = new Node<>();
            head.data = t;
            head.index = size++;
            last = head;
        } else {
            last.right = new Node<>();
            last.right.left = last;
            last = last.right;
            last.data = t;
            last.index = size++;
        }
    }

    @Override
    public void add(int index, T t) {
        Node<T> node = findNodeByIndex(index);
        if (node.index == 0) {
            node.left = new Node<>();
            node.left.right = node;
            node.left.index = 0;
            node.left.data = t;
            head = node.left;
        } else {
            node.left.right = new Node<>();
            node.left.right.index = node.index;
            node.left.right.right = node;
            node.left.right.data = t;
            node.left = node.left.right;
        }
        while (node != null) {
            node.index++;
            node = node.right;
        }
        size++;
    }

    private Node<T> findNodeByIndex(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException("index：" + index);
        }
        Node<T> resultNode = null;
        Node<T> origin = null;
        int half = size >> 1;
        if (index > half) {
            origin = last;
            while (origin.index != index) {
                origin = origin.left;
            }
        } else {
            origin = head;
            while (origin.index != index) {
                origin = origin.right;
            }
        }
        return origin;
    }

    @Override
    public T get(int index) {
        return findNodeByIndex(index).data;
    }

    @Override
    public T remove(int index) {
        Node<T> node = findNodeByIndex(index);
        if (null != node.left) {
            node.left.right = node.right;
        } else {
            node.right.left = null;
            head = node.right;
        }
        if (null != node.right) {
            node.right.left = node.left;
            while (node.right == null) {
                node.right.index--;
            }
        } else {
            node.left.right = null;
            last = node.left;
        }
        size--;
        return node.data;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void enQueue(T t) {
        add(t);
    }

    @Override
    public T deQueue() {
        if (0 >= size) {
            throw new NullPointerException("remove is null");
        }
        T t = null;
        Node<T> node = findNodeByIndex(0);
        if (null != node) {
            t = node.data;
            if (null != node.right) {
                node.right.left = null;
                Node<T> right = node.right;
                head = right;
                while (right != null) {
                    right.index--;
                    right = right.right;
                }
                size--;
            } else {
                head = last = null;
                size = 0;
            }
        }
        return t;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public IIterator<T> iterator() {
        return new LinkedIteratorImpl<>();
    }

    @Override
    public String toString() {
        StringBuilder sbToString = new StringBuilder();
        Node<T> temp = head;
        while (null != temp){
            sbToString.append(temp.toString()).append("\n");
            temp = temp.right;
        }
        return sbToString.toString();
    }

    private static class Node<T> {
        private T data = null;
        private Node<T> left = null;
        private Node<T> right = null;
        private int index = 0;

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", index=" + index +
                    '}';
        }
    }

    private class LinkedIteratorImpl<T> implements IIterator<T> {

        private int index = 0;
        private Node<T> next;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            if (0 == index) {
                next = (Node<T>) head;
            }
            if (null == next) {
                throw new ArrayIndexOutOfBoundsException("next is null");
            }
            Node<T> result = next;
            next = next.right;
            index++;
            return result.data;
        }
    }
}
