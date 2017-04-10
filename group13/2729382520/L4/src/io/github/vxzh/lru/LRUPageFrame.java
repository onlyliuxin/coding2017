package io.github.vxzh.lru;

/**
 * 用双向链表实现LRU算法
 */
public class LRUPageFrame {

    private static class Node {
        Node prev;
        Node next;
        int pageNum;

        Node(Node prev, int pageNum, Node next) {
            this.prev = prev;
            this.pageNum = pageNum;
            this.next = next;
        }
    }

    private int capacity;
    private int size;
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
        if (first != null && first.pageNum == pageNum) {
            return;
        }

        removeNode(pageNum);
        if (size() + 1 > capacity) {
            removeLast();
        }
        addFirst(pageNum);

    }

    private int size() {
        return size;
    }

    private void addFirst(int pageNum) {
        Node f = first;
        Node newNode = new Node(null, pageNum, f);
        if (f == null)
            last = newNode;
        else
            f.prev = newNode;
        first = newNode;
        size++;
    }

    private void removeLast() {

        Node l = last;
        Node prev = l.prev;
        prev.next = null;
        l.prev = null;
        last = prev;
        size--;
    }

    private void removeNode(int pageNum) {
        Node node = first;
        while (node != null) {
            if (node.pageNum == pageNum) {
                if (node == last) {
                    removeLast();
                } else {
                    final Node prev = node.prev;
                    final Node next = node.next;
                    prev.next = next;
                    next.prev = prev;
                    node.prev = null;
                    node.next = null;
                    size--;
                }
                break;
            } else {
                node = node.next;
            }
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