package datastructure.linkedlist;

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

        Node(int pageNum) {
            this.pageNum = pageNum;
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
     * @param key
     * @return
     */
    public void access(int pageNum) {
        if (isEmpty()) {
            initFirstNode(pageNum);
            return;
        }

        if (contains(pageNum)) {
            moveToFirst(pageNum);
        } else {
            insert(pageNum);
        }
    }

    private void initFirstNode(int pageNum) {
        Node node = new Node(pageNum);
        first = last = node;
        size++;
    }

    private void moveToFirst(int pageNum) {
        if (isFirst(pageNum)) {
            return;
        }
        if (isLast(pageNum)) {
            moveLastToFirst();
        } else {
            moveMidToFirst(pageNum);
        }
    }

    private void moveLastToFirst() {
        Node temp = last;

        removeLast();

        addToFirst(temp);
    }

    private void addToFirst(Node temp) {
        temp.next = first;
        first.prev = temp;
        first = temp;
        size++;
    }

    private void moveMidToFirst(int pageNum) {
        Node node = removeMidNode(pageNum);
        addToFirst(node);
    }

    private Node removeMidNode(int pageNum) {
        Node temp = getNode(pageNum);
        temp.prev.next = temp.next;
        temp.next.prev = temp.prev;

        temp.next = null;
        temp.prev = null;

        size--;

        return temp;
    }

    private void insert(int pageNum) {
        if (isFill()) {
            removeLast();
        }
        insertToFirst(pageNum);
    }

    private void removeLast() {
        last = last.prev;
        last.next = null;
        size--;
    }

    private void insertToFirst(int pageNum) {
        Node node = new Node(pageNum);

        addToFirst(node);
    }

    private boolean contains(int pageNum) {
        return indexOf(pageNum) != -1;
    }

    private int indexOf(int num) {
        Node temp = first;
        for (int i = 0; i < size; i++) {
            if (temp.pageNum == num) {
                return i;
            }
            temp = temp.next;
        }
        return -1;
    }

    private Node getNode(int pageNum) {
        Node temp;
        temp = first;
        for (int i = 0; i < size; i++) {
            if (temp.pageNum == pageNum) {
                break;
            }
            temp = temp.next;
        }
        return temp;
    }

    private boolean isEmpty() {
        return size == 0;
    }

    private boolean isFirst(int pageNum) {
        return first.pageNum == pageNum;
    }

    private boolean isLast(int pageNum) {
        return last.pageNum == pageNum;
    }

    private boolean isFill() {
        return size == capacity;
    }

    @Override
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
