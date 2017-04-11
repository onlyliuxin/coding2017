package lru;

/**
 * Created by william on 2017/3/31.
 * 1. 新数据插入到链表头部；
 * 2. 每当缓存命中（即缓存数据被访问），则将数据移到链表头部；
 * 3. 当链表满的时候，将链表尾部的数据丢弃。
 */
public class LRUPageFrame {
    private static class Node {

        Node prev;
        Node next;
        int pageNum;

        Node() {
        }

        @Override
        public String toString() {
            return pageNum + "";
        }
    }

    private int capacity;


    private Node first;// 链表头
    private Node last;// 链表尾
    private int size;

    private void removeLast() {
        if (last != null) {
            if (size == 1) {
                first = last = null;
                size = 0;
            } else {
                last = last.prev;
                last.next = null;
                size--;
            }
        }
    }

    private void remove(Integer pageNum) {
        for (Node temp = first; temp != null; temp = temp.next) {
            if (temp.pageNum == pageNum) {
                unlink(temp);
            }
        }
    }

    private void unlink(Node node) {
        final Node prev = node.prev;
        final Node next = node.next;
        if (prev == null) {
            first = next;
            size--;
        } else if (next == null)
            removeLast();
        else {
            node.next.prev = prev;
            node.prev.next = next;
            size--;
        }
    }

    private void addFirst(Integer pageNum) {
        Node newNode = new Node();
        newNode.pageNum = pageNum;
        if (first == null) {
            first = last = newNode;
        } else {
            newNode.next = first;
            first.prev = newNode;
            first = newNode;
            //在插入第二个元素时设置last元素的前项
            if (size == 1) last.prev = first;
        }
        size++;
    }

    private boolean contains(Integer pageNum) {
        Node temp = first;
        while (temp != null) {
            if (temp.pageNum == pageNum)
                return true;
            temp = temp.next;
        }
        return false;
    }


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
        //存在链表元素
        if (this.contains(pageNum)) {
            remove(pageNum);
            addFirst(pageNum);
        } else {
            //不存在
            if (size == capacity)
                removeLast();
            addFirst(pageNum);
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
