package com.coding.basic.linklist;

/**
 * 用双向链表实现LRU算法
 *
 * @author liuxin
 */
public class LRUPageFrame {

    private static class Node {

        Node prev;
        Node next;
        int pageNum;

        Node() {
        }
    }

    private int capacity;


    private Node first;// 链表头
    private Node last;// 链表尾


    public LRUPageFrame(int capacity) {

        this.capacity = capacity;

    }

    /**
     * 获取缓存中对象
     *
     * @param
     * @return
     */
    public void access(int pageNum) {
        if (first == null) {
            first = new Node();
            first.pageNum = pageNum;
            capacity--;
            return;
        }
        if (getNode(pageNum) == null) {
            if (capacity != 0) {
                Node temp = first;
                first = new Node();
                first.pageNum = pageNum;
                first.next = temp;
                temp.prev = first;
                capacity--;
                if (capacity == 0) {
                    Node las = first;
                    while (las.next != null) {
                        las = las.next;
                    }
                    last = las;
                }
                return;
            }
            Node temp = first;
            first = new Node();
            first.pageNum = pageNum;
            first.next = temp;
            temp.prev = first;
            last = last.prev;
            last.next = null;
            return;

        } else {
            if (getNode(pageNum) == first) {
                return;
            }
            if (getNode(pageNum) == last) {
                last.prev.next = null;
                Node lastTemp = last.prev;
                Node temp = first;
                first = last;
                first.next = temp;
                first.prev = null;
                temp.prev = first;
                last = lastTemp;
                return;
            }
            Node temp = first;
            Node r = getNode(pageNum);

            r.prev.next = r.next;
            r.next.prev = r.prev;
            first = r;
            first.prev = null;
            first.next = temp;

        }


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

    public Node getNode(int pageNum) {
        Node temp = first;
        while (temp.pageNum != pageNum && temp.next != null) {
            temp = temp.next;
        }
        if (temp.pageNum == pageNum) {
            return temp;
        }
        return null;
    }

}
