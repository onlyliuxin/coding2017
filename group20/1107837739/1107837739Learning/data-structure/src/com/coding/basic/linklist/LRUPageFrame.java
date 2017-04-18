package com.coding.basic.linklist;

/**
 * 用双向链表实现LRU算法
 *
 * @author Korben
 */
public class LRUPageFrame {

    private int capacity;
    private int size;
    private Node first; // 链表头
    private Node last;  // 链表尾

    public LRUPageFrame(int capacity) {
        this.capacity = capacity;
    }

    /**
     * 获取缓存中对象
     */
    public void access(int pageNum) {
        if (this.first == null) {
            add2First(pageNum);
            return;
        }

        if (this.first.pageNum == pageNum) {
            return;
        }

        if (reorderCache(pageNum)) {
            return;
        }

        add2First(pageNum);

        if (this.size > this.capacity) {
            removeNode(this.last);
        }
    }

    private boolean reorderCache(int pageNum) {
        Node node = this.first;
        for (int i = 0; i < this.size - 1; i++) {
            node = node.next;
            if (node.pageNum == pageNum) {
                removeNode(node);
                add2First(node.pageNum);
                return true;
            }
        }

        return false;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            this.last = node.prev;
        }
        this.size--;
    }

    private void add2First(int pageNum) {
        Node oldFirst = this.first;
        this.first = new Node(pageNum);
        this.first.next = oldFirst;
        if (oldFirst == null) {
            this.last = this.first;
        } else {
            oldFirst.prev = this.first;
        }

        this.size++;
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        Node node = first;
        while (node != null) {
            buffer.append(node.pageNum);

            node = node.next;
            if (node != null) {
                buffer.append(",");
            }
        }
        return buffer.toString();
    }

    private static class Node {

        Node prev;
        Node next;
        int pageNum;

        Node(int pageNum) {
            this.pageNum = pageNum;
        }
    }
}
