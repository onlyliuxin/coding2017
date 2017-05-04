package com.johnChnia.coding2017.basic.linklist;

/**
 * Created by john on 2017/4/6.
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
     * 获取缓存中的对象
     *
     * @param pageNum 对象值
     */
    public void access(int pageNum) {
        if (first == null) {
            Node node = createNode(pageNum);
            first = last = node;
            capacity--;
        } else if (getNode(pageNum) == null) {
            if (capacity == 0) {
                Node lastNode = first;
                while (lastNode.next != null) {
                    lastNode = lastNode.next;
                }
                lastNode.prev.next = null;
                last = lastNode.prev;
                delete(lastNode);
                capacity++;
            }
            Node node = createNode(pageNum);
            node.next = first;
            node.prev = null;
            first.prev = node;
            first = node;
            capacity--;

        } else {
            if (first.pageNum != pageNum) {
                Node node = getNode(pageNum);
                if (node.next != null) {
                    node.prev.next = node.next;
                    node.next.prev = node.prev;
                } else {
                    node.prev.next = null;
                    last = node.prev;
                }
                node.next = first;
                node.prev = null;
                first.prev = node;
                first = node;
            }
        }

    }

    /**
     * 删除节点
     */
    private void delete(Node node) {
        node.pageNum = 0;
        node.next = null;
        node.prev = null;
    }

    /**
     * @param pageNum 页号
     * @return 节点
     */
    private Node createNode(int pageNum) {
        Node node = new Node();
        node.pageNum = pageNum;
        node.next = null;
        node.prev = null;
        return node;
    }


    /**
     * @param pageNum 页号
     * @return 如果LRUPageFrame包含该pageNum就返回该节点，否则返回null
     */
    private Node getNode(int pageNum) {
        for (Node node = first; node != null; node = node.next) {
            if (node.pageNum == pageNum) {
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
