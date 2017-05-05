package list;

/**
 * @author jiaxun
 */
public class LRUPageFrame {

    private int capacity;
    private Node first;
    private Node last;
    private int size = 0;

    public LRUPageFrame(int capacity) {
        this.capacity = capacity;
    }

    public void access(int pageNum) {
        if (size < capacity) {
            addFirst(pageNum);
        } else {
            Node node = searchNode(pageNum);
            if (node == null) {
                removeLast();
                addFirst(pageNum);
            } else {
                if (node.getData() == first.getData()) return;
                if (node.getData() == last.getData()) {
                    last = node.getPrev();
                    node.getPrev().setNext(null);
                    node.setPrev(null);
                    node.setNext(first);
                    first.setPrev(node);
                    first = node;
                } else {
                    node.getPrev().setNext(node.getNext());
                    node.getNext().setPrev(node.getPrev());
                    node.setNext(first);
                    node.setPrev(null);
                    first = node;
                }
            }
        }
    }

    public Node searchNode(int pageNum) {
        Node curr = first;
        while (curr != null) {
            if (curr.getData() == pageNum) {
                return curr;
            }
            curr = curr.getNext();
        }
        return null;
    }

    public void addFirst(int data) {
        Node node = new Node(data);
        if (first == null) {
            first = node;
        } else if (last == null) {
            last = first;
            first = node;
            first.setNext(last);
            last.setPrev(first);
        } else {
            node.setNext(first);
            first.setPrev(node);
            first = node;
        }
        size++;
    }

    public void addLast(int data) {
        Node node = new Node(data);
        if (first == null) {
            first = node;
        } else if (last == null) {
            last = node;
            first.setNext(last);
            last.setPrev(first);
        } else {
            node.setPrev(last);
            last.setNext(node);
            last = node;
        }
        size++;
    }

    public void removeLast() {
        if (last != null && last.getPrev() != null) {
            Node prev = last.getPrev();
            last.getPrev().setNext(null);
            last = prev.getData() == first.getData() ? null : last.getPrev();
        } else if (first != null) {
            first = null;
        }
        if (size > 0) {
            size--;
        }
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Node curr = first;
        while (curr != null) {
            builder.append(curr.getData());
            if (curr.getNext() != null) {
                builder.append(",");
            }
            curr = curr.getNext();
        }
        return builder.toString();
    }

    private static class Node {

        private int data;
        private Node prev;
        private Node next;

        public Node(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "[data is " + data + "]";
        }
    }
}
