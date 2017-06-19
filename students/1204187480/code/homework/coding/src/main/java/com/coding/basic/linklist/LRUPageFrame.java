package com.coding.basic.linklist;

import lombok.extern.slf4j.Slf4j;

/**
 * 定长链表
 * 命中后更新 pageNumber的位置
 * 随时要考虑 first, node 的变化
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

        public String debug() {
            return new StringBuilder().
                    append("\n##########################pre: ")
                    .append(prev)
                    .append("\n##########################node: ")
                    .append(this)
                    .append("\n##########################next: ")
                    .append(next)
                    .append("\n##########################pageNum: ")
                    .append(pageNum)

                    .toString();
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
     *
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
            debugContent("rmSpareNode");
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
                    first.prev = null;
                } else {
                    nodePre.next = nodeNext;
                    if (nodeNext != null) {
                        nodeNext.prev = nodePre;
                    } else {
                        last = nodePre; // 如果 nodeNext 为空, 说明原先 last 是 node, 现在是 nodePre
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

    private void debugContent(String tag) {
        log.debug("tag={}, currentSize={}, toString={}", tag, currentSize, debug());
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

    public String debug() {
        StringBuilder buffer = new StringBuilder();
        Node node = first;
        while (node != null) {
            buffer
                    .append(node.debug())
                    .append("\n##########################last: ")
                    .append(last)
                    .append("\n##########################capacity: ")
                    .append(capacity)
                    .append("\n##########################toString: ")
                    .append(toString())

            ;

            node = node.next;
            if (node != null) {
                buffer.append("\n,");
            }
        }
        return buffer.toString() + "\n";
    }


}
