package com.aaront.exercise.basic;

import java.util.Optional;

/**
 * 用双向链表实现LRU算法
 *
 * @author tonyhui
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
    private int size = 0;
    private Node first;// 链表头
    private Node last;// 链表尾

    public LRUPageFrame(int capacity) {
        this.capacity = capacity;
    }

    /**
     * 获取缓存中对象
     */
    public void access(int pageNum) {
        Optional<Node> node = getNodeByPageNum(pageNum);
        node.map(r -> {
            delete(r);
            addFirst(r);
            return "";
        }).orElseGet(() -> {
            Node newNode = new Node();
            newNode.pageNum = pageNum;
            addFirst(newNode);
            if (size >= capacity) {
                delete(last);
            } else {
                size++;
            }
            return "";
        });
    }

    private Optional<Node> getNodeByPageNum(int pageNum) {
        Node node = first;
        while (node != null) {
            if (node.pageNum == pageNum) return Optional.of(node);
            node = node.next;
        }
        return Optional.empty();
    }

    private void delete(Node node) {
        if (node == null) return;
        if (node == first) {
            deleteFirst();
            return;
        }
        if (node == last) {
            deleteLast();
            return;
        }
        Node prevNode = node.prev;
        Node nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        node.next = null;
        node.prev = null;
    }

    private void deleteFirst() {
        if (first == null) return;
        first = first.next;
        first.prev = null;
    }

    private void deleteLast() {
        if (last == null) return;
        last = last.prev;
        last.next = null;
    }

    private void addFirst(Node node) {
        if (node == null) return;
        if (first == null) {
            first = node;
            last = node;
        } else {
            node.next = first;
            first.prev = node;
            first = node;
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

}
