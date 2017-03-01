package com.coding.basic;

import java.util.NoSuchElementException;

/**
 * A Simple LinkedList
 *
 * @param <E> element
 */
public class LinkedList<E> implements List<E> {
    /**
     * 链表head
     */
    private Node<E> head;


    /**
     * 链表中元素的个数
     */
    private int size;


    /**
     * 追加一个元素到链表尾
     *
     * @param e
     */
    public void add(E e) {
        Node<E> newNode = new Node<E>(e, null);
        if (this.head == null) {
            this.head = newNode;
        } else {
            Node<E> end = index(size - 1);
            end.next = newNode;
        }
        size++;
    }

    /**
     * 插入一个元素到链表指定位置
     *
     * @param index
     * @param e
     */
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("下标越界");
        }
        if (index == 0) {
            addFirst(e);
        } else if (index == size) {
            addLast(e);
        } else {
            Node<E> indexNode = index(index);
            Node<E> next = indexNode.next;
            Node<E> newNode = new Node<E>(e, next);
            index(index - 1).next = newNode;
            indexNode = null;
            size++;
        }
    }

    /**
     * 获取指定位置的元素
     *
     * @param index
     * @return
     */
    public E get(int index) {
        checkIndex(index);
        return index(index).data;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("下标越界");
        }
    }

    /**
     * 移除指定位置的元素
     *
     * @param index
     * @return
     */
    public E remove(int index) {
        checkIndex(index);
        if (index == 0) {
            return removeFirst();
        } else if (index == (size - 1)) {
            return removeLast();
        } else {
            Node<E> delNode = index(index);
            E e = delNode.data;
            Node<E> prev = index(index - 1);
            prev.next = index(index + 1);
            delNode = null;
            size--;
            return e;
        }
    }

    /**
     * 当前链表的元素个数
     *
     * @return
     */
    public int size() {
        return this.size;
    }

    /**
     * 添加到链表的头
     *
     * @param e
     */
    public void addFirst(E e) {
        Node<E> newNode = new Node<E>(e, null);
        if (this.head != null) {
            newNode.next = this.head;
        }
        this.head = newNode;
        size++;
    }

    /**
     * 添加到链表的尾
     *
     * @param e
     */
    public void addLast(E e) {
        Node<E> newNode = new Node<E>(e, null);
        if (this.head == null) {
            this.head = newNode;
        } else {
            Node<E> end = index(size - 1);
            end.next = newNode;
        }
        size++;
    }

    /**
     * 获取指定位置的节点
     *
     * @param index
     * @return
     */
    private Node<E> index(int index) {
        Node<E> node = this.head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    /**
     * 删除链表第一个元素
     *
     * @return
     */
    public E removeFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        E e = head.data;
        head = head.next;
        size--;
        return e;
    }

    /**
     * 删除链表最后一个元素
     *
     * @return
     */
    public E removeLast() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<E> end = index(size - 1);
        E e = end.data;
        end = null;
        end = index(size - 2);
        end.next = null;
        size--;
        return e;
    }

    /**
     * 节点数据
     *
     * @param <E>
     */
    private static class Node<E> {
        //当前节点存储的数据
        E data;
        //下一个节点
        Node<E> next;

        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    public Iterator iterator(){
        return new LinkedListIterator<>();
    }

    private class LinkedListIterator<E> implements Iterator<E>{

        private int cursor;//游标

        private int lastRet = -1;//可被删除元素下标

        @Override
        public boolean hasNext() {
            return cursor!=size;
        }

        @Override
        public E next() {
            int i = cursor;
            cursor++;
            return (E) LinkedList.this.get(lastRet=i);
        }

        @Override
        public void remove() {
            if(lastRet<0){
                throw new IllegalStateException();
            }
            cursor = lastRet;
            LinkedList.this.remove(lastRet);
            lastRet = -1;
        }
    }
}