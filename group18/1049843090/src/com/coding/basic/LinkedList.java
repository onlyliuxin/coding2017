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
            //TODO index使用太多 遍历元素次数太多
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
        E e = null;
        if (size == 1) {
            e = head.data;
            head = null;
        } else {
            Node<E> lastButOne = index(size - 2);
            Node<E> end = lastButOne.next;
            e = end.data;
            end = null;
            lastButOne.next = null;
        }
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

    public Iterator iterator() {
        return new LinkedListIterator<>();
    }

    private class LinkedListIterator<E> implements Iterator<E> {

        private int cursor;//游标

        private int lastRet = -1;//可被删除元素下标

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public E next() {
            int i = cursor;
            cursor++;
            return (E) LinkedList.this.get(lastRet = i);
        }

        @Override
        public void remove() {
            if (lastRet < 0) {
                throw new IllegalStateException();
            }
            cursor = lastRet;
            LinkedList.this.remove(lastRet);
            lastRet = -1;
        }
    }


    /**
     * 把该链表逆置
     * 例如链表为 3->7->10 , 逆置后变为  10->7->3
     */
    public void reverse() {

    }

    /**
     * 删除一个单链表的前半部分
     * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
     * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
     */
    public void removeFirstHalf() {
        int index = size >> 1;
        if (index > 0) {
            Node node = index(index);
            head = node;
        }
    }

    /**
     * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
     *
     * @param i
     * @param length
     */
    public void remove(int i, int length) {
        if (i + length > size - 1) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> indexNode = index(i);
        Node<E> nextNode = indexNode.next;
        for (int j = i; j < length + i; j++) {
            nextNode = nextNode.next;
        }
        indexNode.next = nextNode;

    }

    /**
     * 假定当前链表和list均包含已升序排列的整数
     * 从当前链表中取出那些list所指定的元素
     * 例如当前链表 = 11->101->201->301->401->501->601->701
     * listB = 1->3->4->6
     * 返回的结果应该是[101,301,401,601]
     *
     * @param list
     */
    public static int[] getElements(LinkedList list) {
        return null;
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 从当前链表中中删除在list中出现的元素
     *
     * @param list
     */

    public void subtract(LinkedList list) {

    }

    /**
     * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
     */
    public void removeDuplicateValues() {

    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
     *
     * @param min
     * @param max
     */
    public void removeRange(int min, int max) {

    }

    /**
     * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
     * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
     *
     * @param list
     */
    public LinkedList intersection(LinkedList list) {
        return null;
    }


}