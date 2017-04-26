package task4.lru;

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
    private int size = 0;

    private Node first;// 链表头
    private Node last;// 链表尾


    public LRUPageFrame(int capacity) {

        this.capacity = capacity;

    }

    /**
     * 获取缓存中对象
     */
    public void access(int pageNum) {
        if (size == 0) {
            last = first = new Node(first, last, pageNum);
//            last = new Node(first, null, pageNum);
            size++;
        } else if (size > 0 && size < capacity) {
            Node _node = get(pageNum);
            if (_node == null) {
                Node newNode = new Node(null, first, pageNum);
                first.prev = newNode;
                first = newNode;
                clear();
                size++;
            } else {
                exchange(_node);
            }
        } else {
            Node _node = get(pageNum);
            if (_node == null) {
                last = last.prev;
                Node newNode = new Node(null, first, pageNum);
                first.prev = newNode;
                first = newNode;
                clear();
            } else exchange(_node);
        }
    }

    private void exchange(Node node) {
        Node nextNode = node.next;
        Node prevNode = node.prev;
        if (prevNode == null)
            return;//头部的话什么都不用做
        if (nextNode != null) {
            nextNode.prev = prevNode;
            prevNode.next = nextNode;
            last = getLast(nextNode);
        } else {
            last = prevNode;
        }
        first.prev = node;
        node.next = first;
        first = node;
        clear();
    }

    private void clear() {
        last.next = null;
        first.prev = null;
    }

    private Node getLast(Node startNode) {
        Node currentNode = startNode;
        while (currentNode.next != null) {
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    private Node get(int pageNum) {
        Node currentNode = last;
        for (int i = 0; i < size; i++) {
            if (pageNum == currentNode.pageNum) {
                return currentNode;
            }
            currentNode = currentNode.prev;
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