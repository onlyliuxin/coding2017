/**
 * Created by bdl19 on 2017/3/29.
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
        initialize();
        //count = 0;
    }

    /**
     * 获取缓存中对象
     *
     * @param
     * @return
     */
    public void access(int pageNum) {
        int index = checkPageNum(pageNum);
        if (index < 0) {
            addNewNode(pageNum);
        } else {
            moveNode(index);
        }

    }

    private void moveNode(int index) {
        Node temp = first;
        if (index == capacity - 1) {
            Node node = last;
            last = last.prev;
            node.prev.next = null;

            node.prev = null;
            node.next = first;


            first.prev = node;
            first = node;
            return;
        }

        if (index == 0) {
            return;
        }

        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }

        temp.prev.next = temp.next;
        temp.next.prev = temp.prev;
        temp.next = first;
        temp.prev = null;
        first.prev = temp;
        first = temp;
    }

    private void addNewNode(int pageNum) {
        Node node = new Node();
        first.prev = node;
        node.next = first;
        first.prev = node;
        node.pageNum = pageNum;
        first = node;
        last = last.prev;
        last.next = null;
    }


    private int checkPageNum(int num) {
        Node node = first;
        int index = 0;
        while (node != null) {
            if (node.pageNum == num) {
                return index;
            }
            index++;
            node = node.next;
        }
        return -1;
    }


    private void initialize() {
        if (capacity <= 0) {
            throw new IndexOutOfBoundsException();
        }
        Node node = new Node();
        first = node;
        last = node;
        for (int i = 0; i < capacity - 1; i++) {
            Node n = new Node();
            Node temp = last;
            last.next = n;
            last = n;
            last.prev = temp;
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
