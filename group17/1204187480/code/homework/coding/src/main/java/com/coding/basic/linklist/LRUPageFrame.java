package com.coding.basic.linklist;

import lombok.extern.slf4j.Slf4j;

/**
 * 定长链表
 * 命中后更新 pageNumber的位置
 */
@Slf4j
public class LRUPageFrame {

    private static class Node {

        Node prev;
        Node next;
        int pageNum;

        public Node(Node next, int pageNum) {
            this.next = next;
            this.pageNum = pageNum;
        }

        public Node(int pageNum) {
            this.pageNum = pageNum;
        }

        Node() {
        }
    }


    private int capacity;

    private int currentSize;
    private Node first;// 链表头
    private Node last;// 链表尾


    public LRUPageFrame(int capacity) {
        this.currentSize = 0;
        this.capacity = capacity;

    }

    /**
     * 获取缓存中对象
     * 新的对象应该放在前面
     * @param pageNum
     * @return
     */
    public void access(int pageNum) {
        if (capacity == 0) {
            return;
        }

        // 如果已经存在, 删除已经存在的
        removeContainedNode(pageNum);

        /**
         * 向前追加
         */
        // 如果 first 为空,   first=last=newNode
        if (first == null && last == null) {
            first = last = new Node(pageNum);
            // 如果不为空 , first=newNode, first.next.pre = first
        } else {
            first = new Node(first, pageNum);
            first.next.prev = first;
        }
        // 修改 size
        currentSize++;
        debugContent("addNewNode");

        // 如果 size = capacity + 1, 去除last (额外考虑 capacity 为 1 的情况), last.next=null
        if (currentSize == capacity + 1) {
            last = last.prev;
            last.next = null;
            currentSize--;
        }
    }

    private Node removeContainedNode(int pageNum) {
        Node node = first;
        while (node != null) {

            if (node.pageNum == pageNum) {
                Node nodePre = node.prev;
                Node nodeNext = node.next;
                if (nodePre == null) {  // 说明在第一个节点就 hit了
                    first = nodeNext;
                } else {
                    nodePre.next = nodeNext;
                    if (nodeNext != null) {
                        nodeNext.prev = nodePre;
                    }
                }
                currentSize--;
                return node;
            }
            node = node.next;
        }
        debugContent("removeContainedNode");
        return null;
    }

    private void debugContent(String  tag) {
        log.debug("tag={}, currentSize={}, toString={}", tag, currentSize, toString());
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
