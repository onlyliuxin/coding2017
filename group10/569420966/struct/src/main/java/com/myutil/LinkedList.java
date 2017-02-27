package com.myutil;

import java.text.MessageFormat;
import java.util.NoSuchElementException;

/**
 * 链表列表
 */
public class LinkedList<T> implements List<T> {
    private Node header = new Node();
    private int size = 0;

    private Node lastNode() {
        return findNode(this.size);
    }

    private Node findNode(int index) {
        int current = 0;
        Node targetNode = header;
        while (current < index) {
            targetNode = targetNode.next;
            current++;
        }
        return targetNode;
    }

    /**
     * 判断边界
     * <p>
     * <pre>
     *      若 index < 0 或者 index > size 则抛出非法参数异常
     * </pre>
     *
     * @param index 当前索引
     */
    private void judgeRange(int index) {
        if (index < 0) {
            throw new IllegalArgumentException(
                    MessageFormat.format("Index is must be great or equal then 0. index:{0}", index));
        }
        if (index >= this.size) {
            throw new IllegalArgumentException(
                    MessageFormat.format("Index is must be less then size(). index:{0}", index));
        }
        if (this.size == Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Array already can not Expansion.");
        }
    }

    public LinkedList() {

    }

    @Override
    public void add(T element) {
        Node lastNode = lastNode();
        Node addNode = new Node();
        addNode.element = element;
        lastNode.next = addNode;
        this.size++;
    }

    @Override
    public void add(T element, int index) {
        judgeRange(index);
        Node targetNode = findNode(index);
        Node addNode = new Node();
        addNode.element = element;
        addNode.next = targetNode.next;
        targetNode.next = addNode;
        this.size++;
    }

    @Override
    public T remove(int index) {
        judgeRange(index);
        Node targetNode = findNode(index);
        Node removeNode = targetNode.next;
        targetNode.next = targetNode.next.next;
        T element = (T) removeNode.element;
        this.size--;
        return element;
    }

    @Override
    public T get(int index) {
        judgeRange(index);
        return (T) findNode(index).next.element;
    }

    @Override
    public int size() {
        return this.size;
    }

    /**
     * 添加一个元素到最开始的位置
     *
     * @param element 元素
     */
    public void addFirst(T element) {
        add(element, 0);
    }

    /**
     * 添加一个元素到最后
     *
     * @param element 元素
     */
    public void addLast(T element) {
        add(element, this.size - 1);
    }

    /**
     * 删除第一个元素
     *
     * @return 第一个元素
     */
    public T removeFirst() {
        if (this.size == 0) {
            throw new ArrayIndexOutOfBoundsException("This list is empty, don't to remove.");
        }

        return remove(0);
    }

    /**
     * 删除最后一个元素
     *
     * @return 最后一个元素
     */
    public T removeLast() {
        if (this.size == 0) {
            throw new ArrayIndexOutOfBoundsException("This list is empty, don't to remove.");
        }
        return remove(this.size - 1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node indexNode = header.next;
        while (indexNode != null) {
            sb.append((T) indexNode.element);
            if (indexNode.next != null) {
                sb.append(",");
            }
            indexNode = indexNode.next;
        }
        sb.append("]");

        return sb.toString();
    }

    /**
     * 获取迭代器
     *
     * @return 迭代器
     */
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        int position = 0;
        int lastRet = -1;

        public boolean hasNext() {
            return position < LinkedList.this.size();
        }

        public T next() {
            if (position >= size) {
                throw new NoSuchElementException();
            }
            int i = position;
            T element = LinkedList.this.get(position++);
            lastRet = i;
            return element;
        }

        public T remove() {
            if (lastRet < 0) {
                throw new IllegalStateException();
            }
            T removeElement = LinkedList.this.remove(lastRet);
            position = lastRet;
            lastRet = -1;
            return removeElement;
        }
    }

    private static class Node<T> {
        T element;
        Node next;
    }

    public static void main(String[] args) {
        LinkedList<Integer> ids = new LinkedList<>();
        for (int i = 0; i < 11; i++) {
            ids.add(i);
        }
        Iterator iterator = ids.iterator();
        System.out.println(ids);
    }
}
