package com.coding.basic.datastructure.linklist;

/**
 * 用双向链表实现LRU算法
 *
 * @author zt
 */
public class LRUPageFrame {

    private int capacity;
    private Node first;
    private Node last;
    private int size = 0;

    public LRUPageFrame(int capacity) {
        this.capacity = capacity;
    }

    /**
     * 获取缓存中对象
     */
    public void access(int pageNum) {
        if (first == null) {
            first = new Node();
            first.pageNum = pageNum;
            last = first;
            size++;
        } else {
            if (size < capacity) {
                linkFirst(pageNum);
                size++;
            } else {
                Node p = first;
                int count = 0;
                while (p != null) {
                    if (p.pageNum == pageNum) {
                        if (p == first) {
                            break;
                        } else if (p == last) {
                            last = p.prev;
                            p.prev.next = null;
                            linkNodeToFirst(p);
                        } else {
                            p.next.prev = p.prev;
                            p.prev.next = p.next;
                            linkNodeToFirst(p);
                        }
                        break;
                    }
                    p = p.next;
                    count++;
                }
                // if doesn't contains the same value
                if (count >= size) {
                    linkFirst(pageNum);
                    removeLast();
                }
            }
        }
    }

    private void linkNodeToFirst(Node p) {
        first.prev = p;
        p.next = first;
        p.prev = null;
        first = p;
    }

    private void linkFirst(int pageNum) {
        Node node = new Node();
        node.pageNum = pageNum;
        first.prev = node;
        node.next = first;
        first = node;
    }

    private void removeLast() {
        last = last.prev;
        last.next = null;
    }

    @Override
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

        Node() {

        }
    }

}
