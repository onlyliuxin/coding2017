package com.coding.basic;

import java.util.NoSuchElementException;

/**
 * Created by wanc on 2017/2/21.
 * 实现单向链表集合
 */
public class LinkedList implements List {
    /**
     * 首节点
     */
    private Node head;
    /**
     * 计数
     */
    private int size = 0;

    /**
     * 检查是否越界 利用jdk源码的检测方法
     */
    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    /**
     * JDK 源码检测方法
     *
     * @param index
     * @return
     */
    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    /**
     * JDK 源码 错误信息
     *
     * @param index
     * @return
     */
    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    /**
     * JDK 源码检测方法
     *
     * @param index
     * @return
     */
    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    /**
     * JDK 源码检测方法
     *
     * @param index
     * @return
     */
    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    /**
     * 获取对应下标的节点
     */
    Node node(int index) {
        Node x = head;
        for (int i = 0; i < index; i++)
            x = x.next;
        return x;
    }

    /**
     * 在末尾添加数据
     *
     * @param o
     */
    public void add(Object o) {

        if (head == null)
            head = new Node(o, null);
        else {
            final Node lastNode = node(size - 1);
            final Node newNode = new Node(o, null);
            lastNode.next = newNode;
        }
        size++;
    }

    /**
     * 指定位置添加数据
     *
     * @param index
     * @param o
     */
    public void add(int index, Object o) {
        checkPositionIndex(index);
        if (size == index)
            add(o);
        else {
            final Node prevNode = node(index - 1);
            final Node nextNode = prevNode.next;
            final Node newNode = new Node(o, nextNode);
            prevNode.next = newNode;
            size++;
        }
    }

    /**
     * 获取指定索引数据
     *
     * @param index
     * @return
     */
    public Object get(int index) {
        return node(index).data;
    }

    /**
     * 移除指定索引数据
     *
     * @param index
     * @return
     */
    public Object remove(int index) {
        checkElementIndex(index);
        final Node prevNode = node(index - 1);
        final Node x = prevNode.next;
        if (index - 1 < 0) {
            prevNode.next = null;
            head = x;
        } else {
            final Node nextNode = x.next;
            prevNode.next = nextNode;
            x.next = null;
        }
        size--;
        return x.data;
    }

    /**
     * 返回数量
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 在链首添加数据
     *
     * @return
     */
    public void addFirst(Object o) {
        final Node h = head;
        final Node newNode = new Node(o, h);
        head = newNode;
        size++;
    }

    /**
     * 在链尾添加数据
     *
     * @return
     */
    public void addLast(Object o) {
        add(o);
    }

    /**
     * 移除链首数据
     *
     * @return
     */
    public Object removeFirst() {
        final Node h = head;
        if (h == null)
            throw new NoSuchElementException();
        final Node newFirst = h.next;
        h.next = null;
        head = newFirst;
        size--;
        return h.data;
    }

    /**
     * 移除链尾数据
     *
     * @return
     */
    public Object removeLast() {
        final Node prev = node(size - 1 - 1);
        final Node l = prev.next;
        prev.next = null;
        l.next = null;
        size--;
        return l.data;
    }

    /**
     * 获取迭代器
     *
     * @return
     */
    public Iterator iterator() {
        return new LinkedItr();
    }

    /**
     * 迭代器实现内部类
     *
     * @return
     */
    private class LinkedItr implements Iterator {
        int cursor;//游标

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public Object next() {
            int i = cursor;
            if (i > size - 1) throw new NoSuchElementException();
            Node current = node(i);
            if (current == null) throw new IndexOutOfBoundsException();
            cursor = i + 1;
            return current.data;
        }
    }

    /**
     * 节点内部类 用于保存数据
     */
    private static class Node {
        Object data;
        Node next;

        Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    /**
     * 重写toString 方便打印
     *
     * @return
     */
    @Override
    public String toString() {
        String result = "[";
        for (int i = 0; i < size; i++) {
            Node n = node(i);
            if (i == 0)
                result += n.data;
            else
                result += "," + n.data;

        }

        return result + "]";
    }
}
