package com.coding.basic.linklist;

import java.util.Objects;

/**
 * 用双向链表实现LRU算法
 */
@SuppressWarnings("unchecked")
public class LRUPageFrame<T> {

    // 容量
    private int capacity;

    private Node first;// 链表头
    private Node last;// 链表尾

    public LRUPageFrame(int capacity) {
        this.capacity = capacity;
    }

    /**
     * 获取缓存中对象
     * 
     * @param pagNum
     * @return
     */
    public void access(int pagNum) {
        if (first == null) {
            first = new Node();
            first.setPageNum(pagNum);
            return;
        }
        if (last == null) {
            last = new Node();
            last.pageNum = first.pageNum;
            first.pageNum = pagNum;
            first.next = last;
            last.prev = first;
            return;
        }
        addNode(pagNum);
    }

    private void addNode(int pagNum) {
        // 找得到
        Node node = findNode(pagNum);
        if (node == null) {
            node = new Node();
            node.setPageNum(first.getPageNum());
            first.pageNum = pagNum;
            first.next.prev = node;
            node.next = first.next;
            first.next = node;
            node.prev = first;
        } else {
            if (node.prev == null) {
                return;
            } else if (node.next == null) {
                node.prev.next = null;
            } else {
                node.next.prev = node.prev;
                node.prev.next = node.next;
            }
            node = new Node();
            node.pageNum = first.pageNum;
            node.next = first.next;
            first.next.prev = node;
            node.prev = first;
            first.next = node;
            first.pageNum = pagNum;
        }
        Node tmp = first;
        int i = 1;
        while (tmp.next != null && i < capacity) {
            tmp = tmp.next;
            i++;
        }
        tmp.next = null;
        last = tmp;
    }

    private Node findNode(int pageNum) {
        Node tmp = first;
        while (tmp != null) {
            if (Objects.equals(tmp.getPageNum(), pageNum)) {
                return tmp;
            }
            tmp = tmp.next;
        }
        return null;
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        Node node = first;
        while (node != null) {
            buffer.append(node.getPageNum());
            node = node.next;
            if (node != null) {
                buffer.append(",");
            }
        }
        return buffer.toString();
    }

    private static class Node<T> {

        Node prev;

        Node next;

        int pageNum;

        Node() {
        }

        public int getPageNum() {
            return pageNum;
        }

        public Node getNext() {
            return next;
        }

        public Node getPrev() {
            return prev;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }
    }

}
