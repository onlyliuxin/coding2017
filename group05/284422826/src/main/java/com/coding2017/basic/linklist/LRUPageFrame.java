package com.coding2017.basic.linklist;

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

    private int size = 0;

    public LRUPageFrame(int capacity) {
        this.capacity = capacity;
    }

    /**
     * 获取缓存中对象
     *
     * @param pageNum
     * @return
     */
    public void access(int pageNum) {
        if (size < capacity) {
            Node node = new Node();
            node.pageNum = pageNum;
            if (first == null && last == null) {
                node.prev = null;
                node.next = null;
                first = node;
                last = node;
            } else {
                if (last.prev == null) {
                    last.prev = node;
                    node.next = last;
                } else {
                    assert first != null;
                    first.prev = node;
                    node.next = first;
                }
                node.prev = null;
                first = node;
            }
            size++;
        } else {
            Node node = last;
            while (node != null) {
                if (pageNum == node.pageNum) {
                    Node temp = node;
                    if(node == last){
                        last = last.prev;
                        last.prev = temp.prev.prev;
                        last.next = null;
                    }else if(node == first){
                        first = temp.next;
                        first.prev = null;
                        first.next = temp.next.next;
                    }else{
                        node.next.prev = temp.prev;
                        node.prev.next = temp.next;
                    }
                    temp = null;
                    break;
                }
                node = node.prev;
            }

            if(node == null){
                Node temp = last;
                last = last.prev;
                last.prev = temp.prev.prev;
                last.next = null;
                temp = null;
            }

            Node newNode = new Node();
            newNode.pageNum = pageNum;
            first.prev = newNode;
            newNode.prev = null;
            newNode.next = first;
            first = newNode;

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
