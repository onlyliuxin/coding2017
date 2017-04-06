package com.coding2017.week4.lru;

/**
 * 用双向链表实现LRU算法
 * 
 * @author liuxin
 *
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
    private int size; // 当前个数

    public LRUPageFrame(int capacity) {
        this.capacity = capacity;
        size = 0;
    }

    /**
     * 获取缓存中对象
     * 
     * @param pageNum
     * @return
     */
    public void access(int pageNum) {
        Node node = query(pageNum);
        if (node == null) {
            Node newNode = new Node();
            newNode.pageNum = pageNum;
            accessNotExist(newNode);
        } else {
            accessExist(node);
        }
    }

    private void accessExist(Node node) {
        removeNode(node);
        addFirst(node);
    }

    /**
     * 此处没有要求传入的node的prev和next, 所以需要自己处理
     * 
     * @param node
     */
    private void addFirst(Node node) {
        node.prev = null;
        node.next = null;
        if (first == null) {
            first = node;
            last = node;
        } else {
            first.prev = node;
            node.next = first;
            first = node;
        }
        size++;
    }

    /**
     * 需要考虑删除的节点是头结点, 或尾节点的情况
     */
    private void removeNode(Node node) {
        if (node.prev == null) {
            first = node.next;
        }
        if (node.next == null) {
            last = node.prev;
        }
        if (node.prev != null) {
            node.prev.next = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        }
        size--;
    }

    /**
     * 如果已经满了, 则挤出去一个, 然后追加
     * 
     * @param node
     */
    private void accessNotExist(Node node) {
        if (size == capacity) {
            removeLast();
        }
        addFirst(node);
    }

    private void removeLast() {
        last.prev.next = null;
        last = last.prev;
        size--;
    }

    private Node query(int pageNum) {
        for (Node node = first; node != null; node = node.next) {
            if (pageNum == node.pageNum) {
                return node;
            }
        }
        return null;
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

}
