package linklist;


public class LRUPageFrame {

    private static class Node {

        Node prev;
        Node next;
        int pageNum;

        Node() {
        }

        public Node(Node prev, Node next, int pageNum) {
            this.prev = prev;
            this.next = next;
            this.pageNum = pageNum;
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
     *
     * @param pageNum
     * @return
     */
    public void access(int pageNum) {
        Node node = first;
        boolean flag = false;
        while (node != null) {
            //是否存在，提到最近访问的位置，也就是first
            if (node.pageNum == pageNum) {
                flag = true;
                //该节点为first,不做操作，直接退出
                if (node.prev == null) {
                    break;
                }

                first = new Node(null, first, pageNum);
                first.next.prev = first;

                node.prev.next = node.next;
                if (node.next != null) {
                    node.next.prev = node.prev;
                } else {
                    last = node.prev;
                }
                node.next = null;
                node.prev = null;
                break;
            }
            node = node.next;
        }
        if (!flag) {
            if (currentSize == 0) {
                first = new Node(null, null, pageNum);
                last = first;
                currentSize++;
                return;
            }
            if (currentSize < capacity) {
                first = new Node(null, first, pageNum);
                first.next.prev = first;
                currentSize++;
                return;
            } else {
                if (capacity == 1) {
                    first = new Node(null, null, pageNum);
                    last = first;
                } else {
                    first = new Node(null, first, pageNum);
                    first.next.prev = first;
                    last = last.prev;
                    last.next.prev = null;
                    last.next = null;
                }
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
