package com.kevin.coding01.basic;

/**
 * Created by YinWenBing on 2017/2/25.
 */
public class MyLinkedList<E> implements MyList<E> {
    private Node<E> first;//头节点
    private int size = 0;//默认大小为0

    public void add(E e) {
        if (size == 0) {
            first = new Node<E>();
            first.element = e;
            size++;
        } else {
            Node head = first;
            for (int i = 0; i < size - 1; i++) {
                head = head.next;
            }

            Node add = new Node();
            add.element = e;
            head.next = add;
            size++;
        }
    }


    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index:" + index + ";size:" + size);
        }
        Node prev = getNode(index - 1);//当前索引指向的节点的上一节点
        Node next = getNode(index);//当前索引指向的节点成为添加节点的next
        Node add = new Node();
        add.element = e;
        prev.next = add;
        add.next = next;
        size++;
    }

    private Node<E> getNode(int index) {
        Node node = first;

        for (int i = 0; i < index; i++) {
            node = first.next;
        }

        return node;
    }

    public E get(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("index:" + index + ";size:" + size);
        }

        if (size == 0) {
            return null;
        }

        return getNode(index).element;
    }

    public E remove(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("index:" + index + ";size:" + size);
        }

        Node prev = getNode(index - 1);
        Node next = getNode(index + 1);

        prev.next = next;
        return getNode(index).element;
    }

    public int size() {
        return size;
    }

    public void addFirst(E e) {
        if (size == 0) {
            first = new Node<E>();
            first.element = e;
            size++;
        } else {
            Node add = new Node();
            add.element = e;
            add.next = first;
            first = add;
            size++;
        }
    }

    public void addLast(E e) {
        Node oldLast = getNode(size - 1);
        Node add = new Node();
        add.element = e;
        oldLast.next = add;
        size++;
    }

    public E removeFirst() {
        Node oldFirst = first;
        if (first.next != null) {
            first = first.next;
            size--;
            return (E) oldFirst.element;
        } else {//只有一个节点或者一个节点也没有
            first = null;
            return null;
        }
    }

    public E removeLast() {
        Node last = getNode(size - 1);
        if (last != null) {
            E element = (E) last.element;
            Node newLast = getNode(size - 2);
            newLast.next = null;
            size--;
            return element;
        } else { //一个节点都不存在
            return null;
        }
    }

    /**
     * 静态内部类
     */
    private static class Node<E> {
        E element;//节点数据
        Node<E> next;//下一节点
    }

}
