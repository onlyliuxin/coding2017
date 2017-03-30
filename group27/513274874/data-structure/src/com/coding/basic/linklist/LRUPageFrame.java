package com.coding.basic.linklist;

import java.util.Objects;

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

        public boolean hasNext() {
            return next != null;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node node = (Node) o;
            return Objects.equals(pageNum, node.pageNum);
        }

        @Override
        public int hashCode() {
            return Objects.hash(pageNum);
        }
    }

    private int capacity;//最大存储个数
    private int cur;//当前存储个数


    private Node first;// 链表头
    private Node last;// 链表尾


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
        if(this.cur == 0 ) {
            Node node = new Node();
            node.pageNum = pageNum;
            addFirst(node);
            return;
        }

        if (get(pageNum) != null) {
            pop(pageNum);
        } else {
            Node node = new Node();
            node.pageNum = pageNum;
            add(node);
        }
    }

    private void add(Node node) {
        addFirst(node);
    }

    private Node get(int pageNum) {
        Node val = this.first;
        while (val.hasNext()) {
            if (val.pageNum == pageNum) {
                return val;
            }
            val = val.next;
        }
        return null;
    }

    private void addFirst(Node node) {
        if(cur == 0){
            this.first = node;
            this.last = node;
        }else {
            Node oldFirst = this.first;
            this.first = node;
            node.prev = null;
            node.next = oldFirst;

            oldFirst.prev = node;
        }
        this.cur++;

        if (cur > capacity) {
            removeLast();
        }
    }
    private void removeLast(){
        Node oldLast = this.last;
        this.last = oldLast.prev;
        oldLast.prev.next = null;

        oldLast = null;
    }

    /**
     * 将节点变成first
     *
     * @param pageNum
     */
    private void pop(int pageNum) {
        Node node = this.get(pageNum);

        //根据node的位置确定如何位移
        if (node.equals(this.first)) {
            return;
        } else {
            Node oldPre = node.prev;
            Node oldNext = node.next;
            Node oldFirst = this.first;

            this.first = node;
            node.prev = null;
            node.next = oldFirst;

            oldPre.next = oldNext;
            oldNext.prev = oldPre;


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
