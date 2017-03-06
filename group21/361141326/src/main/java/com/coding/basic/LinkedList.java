package com.coding.basic;

/**
 * 单向链表
 */
public class LinkedList<T> implements List<T> {

    /**
     * 首节点
     */
    private Node<T> head;

    /**
     * 尾节点
     */
    private Node<T> last;

    private int size;

    public void add(T t) {
        addLast(t);
    }

    public void add(int index, T t) {
        rangeCheck(index);

        Node<T> previous = node(index);
        Node<T> next = previous.next;

        Node<T> node = new Node<>(previous, t, next);
        previous.next = node;
        next.previous = node;

        size ++;
    }

    public T get(int index) {
        rangeCheck(index);

        return node(index).data;
    }

    public T remove(int index) {
        rangeCheck(index);

        Node<T> node = node(index);
        node.previous.next = node.next;

        size --;
        return node.data;
    }

    public int size() {
        return size;
    }

    public void addFirst(T t) {
        Node<T> h = head;
        Node<T> node = new Node<>(null, t, h);
        head = node;
        if (h == null) {
            // 初始化尾节点
            last = node;
        } else {
            // 原有第一个节点，变为第二个节点
            h.previous = node;
        }

        size++;
    }

    public void addLast(T t) {
        Node<T> l = last;
        Node<T> node = new Node<>(last, t, null);
        last = node;
        if (l == null) {
            // 初始化首节点
            head = node;
        } else {
            // 原有的最后一个节点，变为倒数第二个节点
            l.next = node;
        }

        size++;
    }

    public T removeFirst() {
        if (head == null) return null;

        Node<T> next = head.next;
        T data = head.data;
        head = next;
        if (next == null) {
            last = null;
        } else {
            // 首节点前一个节点为空
            next.previous = null;
        }

        size--;
        return data;
    }

    public T removeLast() {
        if (last == null) return null;

        Node<T> previous = last.previous;
        T data = last.data;
        last = previous;

        if (previous == null) {
            head = previous;
        } else {
            previous.next = null;
        }

        size--;
        return data;
    }

    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T> {

        /**
         * 迭代器当前指向的位置
         */
        private int currentPoint;

        @Override
        public boolean hasNext() {
            return currentPoint < size;
        }

        @Override
        public T next() {
            return get(currentPoint ++);
        }
    }

    private void rangeCheck(int index) {
        if (index > size - 1) throw new IndexOutOfBoundsException("index:" + index + ", size:" + size);
    }

    /**
     * 获取对应的元素，如果i > size / 2 则从尾节点开始遍历，反之则从首节点开会遍历, 避免index在最后，顺序循环的浪费性能
     *
     * @param index 指定的位置
     * @return 元素
     */
    private Node<T> node(int index) {
        if (index > (size / 2)) {
            Node<T> node = last;
            for (int i = size - 1; i > index; i --) {
                node = node.previous;
            }
            return node;
        } else {
            Node<T> node = head;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        }
    }

    private static class Node<T> {

        // 上一个
        Node<T> previous;

        T data;

        // 下一个
        Node<T> next;

        Node(Node<T> pre, T data, Node<T> next) {
            this.previous = pre;
            this.data = data;
            this.next = next;
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> node = head;
        while(node != null) {
            sb.append(node.data).append(",");
            node = node.next;
        }

        sb.delete(sb.length() - 1, sb.length()).append("]");

        return sb.toString();
    }
}
