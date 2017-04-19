package list;

/**
 * @author jiaxun
 */
public class SinglyLinkedList implements List {

    private Node head;
    private int size;

    public SinglyLinkedList() {
        size = 0;
    }

    public void addFirst(Object data) {
        Node node = new Node(data);
        node.setNext(head);
        head = node;
        size++;
    }

    public Node removeFirst() {
        Node object = head;
        head = object.getNext();
        size--;
        return object;
    }

    public Node removeLast() {
        Node curr = head;
        Node prev = null;
        while (curr != null) {
            prev = curr;
            curr = curr.getNext();
        }
        if (prev != null) {
            prev.setNext(null);
        }
        size--;
        return curr;
    }

    public Node get(int index) {
        if (index > size) {
            throw new IndexOutOfBoundsException();
        }
        Node curr = head;
        while (curr != null) {
            if (index == 0)
                break;
            curr = curr.getNext();
            index--;

        }
        return curr;
    }

    public Node remove(int index) {
        Node curr = head;
        Node prev = null;
        while (curr != null) {
            if (index == 0)
                break;
            prev = curr;
            curr = curr.getNext();
            index--;
        }
        if (prev != null) {
            prev.setNext(curr.getNext());
            curr.setNext(null);
        }
        size--;
        return curr;
    }

    public void addLast(Object object) {
        if (head == null) {
            head = new Node(object);
        } else {
            Node curr = head;
            Node prev = null;
            while (curr != null) {
                prev = curr;
                curr = curr.getNext();
            }
            prev.setNext(new Node(object));
        }
        size++;
    }

    @Override
    public void add(Object o) {

    }

    public void add(int index, Object object) {
        Node curr = head;
        Node prev = null;
        while (curr != null) {
            if (index == 0)
                break;
            prev = curr;
            curr = curr.getNext();
            index--;
        }
        if (prev != null) {
            Node newNode = new Node(object);
            newNode.setNext(curr);
            prev.setNext(newNode);
            size++;
        }
    }

    public int size() {
        return size;
    }

    public Iterator iterator() {
        return new SinglyLinkedListIterator(this);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Node current = head;
        while (current != null) {
            builder.append(current.toString());
            current = current.getNext();
        }
        return builder.toString();
    }

    private class SinglyLinkedListIterator implements Iterator {

        private SinglyLinkedList linkedList;
        private int currentPosition = 0;

        public SinglyLinkedListIterator(SinglyLinkedList linkedList) {
            this.linkedList = linkedList;
        }

        @Override
        public boolean hasNext() {
            return currentPosition < size;
        }

        @Override
        public Object next() {
            return linkedList.get(currentPosition++);
        }

        @Override
        public Object remove() {
            return linkedList.remove(--currentPosition);
        }
    }

    private static class Node {

        private Object data;
        private Node next;

        public Node(Object data) {
            this.data = data;
        }

        public Object getData() {
            return data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "[data is " + getData() + "]";
        }
    }

}
