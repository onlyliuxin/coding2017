package com.coding.basic;

import java.util.Date;
import java.util.Objects;

@SuppressWarnings("unchecked")
public class LinkedList<T extends Comparable> implements List<T> {

    private Node head;

    private int size = 0;

    public void add(T data) {
        if (head == null) {
            head = new Node(data);
        }
        Node tmp = head;
        while (tmp.next != null) {
            tmp = tmp.next;
        }
        tmp.next = new Node(data);
        size++;
    }

    public void add(int index, T data) {
        if (head == null || index > size - 1 || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            head.setNext(new Node(data));
        }
        Node tmp = head;
        for (int i = 1; i < index; i++) {
            tmp = tmp.next;
        }
        tmp.setNext(new Node(data));
        size++;
    }

    public T get(int index) {
        if (head == null || index > size - 1 || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            return (T) head.getData();
        }
        Node tmp = head;
        for (int i = 1; i < index; i++) {
            tmp = tmp.next;
        }
        return (T) tmp.getData();
    }

    public T remove(int index) {
        if (head == null || index > size - 1 || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            head = head.next;
            return (T) head.getData();
        }
        Node tmp = head;
        for (int i = 1; i <= index - 1; i++) {
            tmp = tmp.next;
        }
        T result = (T) tmp.next.data;
        tmp.setNext(tmp.next.next);
        size--;
        return result;
    }

    public int size() {
        return size;
    }

    public void addFirst(T data) {
        if (head == null) {
            throw new IndexOutOfBoundsException();
        }
        Node nHead = new Node(data);
        nHead.setNext(head);
        head = nHead;
        size++;
    }

    public void addLast(T data) {
        if (head == null) {
            throw new IndexOutOfBoundsException();
        }
        Node tmp = head;
        while (tmp.next != null) {
            tmp = tmp.next;
        }
        tmp.setNext(new Node(data));
        size++;
    }

    public T removeFirst() {
        if (head == null) {
            throw new IndexOutOfBoundsException();
        }
        T result = (T) head.data;
        head = head.next;
        size--;
        return result;
    }

    public T removeLast() {
        if (head == null) {
            throw new IndexOutOfBoundsException();
        }
        Node tmp = head;
        while (tmp.next.next != null) {
            tmp = tmp.next;
        }
        T result = (T) tmp.data;
        tmp.setNext(null);
        size--;
        return result;
    }

    public Iterator iterator() {
        return new LinkListIterator(head);
    }

    private class LinkListIterator implements Iterator {

        private Node cache;

        LinkListIterator(Node cache) {
            cache = head;
        }

        @Override
        public boolean hasNext() {
            return cache.getNext() == null;
        }

        @Override
        public Node next() {
            return cache.getNext();
        }
    }

    private static class Node<T> {

        T data;

        Node next;

        Node() {

        }

        Node(T data) {
            this.data = data;
            this.next = null;
        }

        Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Node getNext() {
            return next;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    /**
     * 把该链表逆置 例如链表为 3->7->10 , 逆置后变为 10->7->3
     */
    public void reverse() {
        if (size <= 1) {
            return;
        }
        Node tmp = head;
        Node tail = new Node(head.data);
        Node cache = null;
        while (tmp.next != null) {
            cache = new Node(head.next.data);
            cache.next = tail;
            tail = cache;
        }
        head = cache;
    }

    /**
     * <pre>
     * 删除一个单链表的前半部分 例如：list = 2->5->7->8 ,
     * 删除以后的值为 7->8 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
     * </pre>
     */
    public void removeFirstHalf() {
        for (int i = 0; i < size / 2; i++) {
            head = head.next;
        }
    }

    /**
     * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
     * 
     * @param i
     * @param length
     */
    public void remove(int i, int length) {
        if (i + length > size) {
            throw new IndexOutOfBoundsException();
        }
        Node tmp = head;
        for (int index = 0; index < i; index++) {
            tmp = tmp.next;
        }
        Node cache = tmp;
        for (int index = 0; index < length; index++) {
            cache = cache.next;
        }
        tmp.next = cache.next;
    }

    /**
     * <pre>
     * 假定当前链表和list均包含已升序排列的整数 从当前链表中取出那些list所指定的元素 
     * 例如当前链表 = 11->101->201->301->401->501->601->701 listB = 1->3->4->6
     * 返回的结果应该是[101,301,401,601]
     * </pre>
     *
     */
    public T[] getElements(LinkedList<Integer> list) {
        Iterator iterator = list.iterator();
        int index = 0;
        int flag = 0;
        T[] result = (T[]) new Object[list.size];
        while (iterator.hasNext()) {
            Node next = (Node) iterator.next();
            index = (int) next.getData();
            result[flag++] = get(index);
        }
        return result;
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 从当前链表中中删除在list中出现的元素
     * 
     * @param list
     */

    public void subtract(LinkedList<T> list) {
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            T data = (T) iterator.next();
            Node tmp = head;
            if (Objects.equals(tmp.data, data)) {
                head = head.next;
            } else {
                Node cache = tmp.next;
                while (tmp.next != null) {
                    if (Objects.equals(cache.data, data)) {
                        cache = cache.next;
                    }
                }
            }
        }
    }

    /**
     * <pre>
     * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。 
     * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
     * </pre>
     */
    public void removeDuplicateValues() {
        if (size <= 1) {
            return;
        }
        Node tmp = head;
        T data = (T) tmp.data;
        while (tmp != null && tmp.next != null) {
            if (Objects.equals(data, tmp.next.data)) {
                tmp = tmp.next.next;
            }
        }
    }

    /**
     * <pre>
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 试写一高效的算法，
     * 删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
     * </pre>
     * 
     * @param min
     * @param max
     */
    public void removeRange(T min, T max) {
        Node tmp = head;
        Node begin = null;
        Node end = null;
        if (((T) head.data).compareTo(min) < 0) {
            tmp = head.next;
            while (tmp.next != null) {
                if (begin == null && ((T) tmp.next.data).compareTo(min) > 0) {
                    begin = tmp;
                } else if (((T) tmp.next.data).compareTo(min) < 0) {
                    end = tmp;
                }
            }
        } else {
            begin = head;
        }
        if (Objects.equals(begin, tmp)) {
            head = end.next == null ? null : end.next;
        } else {
            head = begin;
        }
    }

    /**
     * <pre>
     * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
     * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
     * </pre>
     * 
     * @param list
     */
    public LinkedList<T> intersection(LinkedList<T> list) {
        LinkedList<T> intersection = new LinkedList<T>();
        int leftIndex = 0;
        int rightIndex = 0;
        while (leftIndex < size && rightIndex < list.size) {
            T left = get(leftIndex);
            T right = list.get(rightIndex);
            if (left.compareTo(right) == 0) {
                intersection.add(left);
                leftIndex++;
                rightIndex++;
            } else if (left.compareTo(right) > 0) {
                rightIndex++;
            } else {
                leftIndex++;
            }
        }
        return intersection;
    }
}
