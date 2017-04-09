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
        addLast(o);
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

    public void reverse() {
        if (head == null || head.getNext() == null) return;
        Node prev = null;
        Node next = null;
        Node curr = head;
        while (curr != null) {
            next = curr.getNext();
            curr.setNext(prev);
            prev = curr;
            curr = next;
        }
        head = prev;
    }

    public void removeFirstHalf() {
        if (head == null) return;
        int half = size / 2;
        Node curr = head;
        while (half != 0) {
            curr = curr.getNext();
            half--;
        }
        head = curr;
    }

    public void remove(int i, int length) {
        if (head == null || length == 0 || i >= size) return;
        if (i + length >= size) length = size - i - 1;
        Node prev = head;
        Node curr = head;
        int firstPos = i;
        while (curr != null) {
            if (firstPos == 0)
                break;
            prev = curr;
            curr = curr.getNext();
            firstPos--;
        }
        int lastPos = length - i;
        while (curr != null) {
            if (lastPos == 0)
                break;
            curr = curr.getNext();
            lastPos--;
        }
        prev.setNext(curr == null ? null : curr.getNext());
    }

    public int[] getElements(SinglyLinkedList list) {
        if (list == null || list.size() == 0) return null;
        int[] resultList = new int[list.size()];
        int offset = 0;
        int count = 0;
        Node curr = head;
        for (int i = 0, len = list.size(); i < len; i++) {
            int index = (int) list.get(i).getData();
            index = index - offset;
            offset = (int) list.get(i).getData();
            while (curr != null) {
                if (index == 0) {
                    resultList[count++] = (int) curr.getData();
                    break;
                }
                curr = curr.getNext();
                index--;
            }
        }
        return resultList;
    }

    public void subtract(SinglyLinkedList list) {
        if (head == null || list == null) return;
        Node curr = head;
        Node prev = null;
        int bCount = 0;
        while (curr != null) {
            if (bCount == list.size()) break;
            int currData = (int) curr.getData();
            int bData = (int) list.get(bCount).getData();
            if (currData == bData) {
                if (prev != null) {
                    prev.setNext(curr.getNext());
                } else {
                    head = curr.getNext();
                }
                bCount++;
            } else {
                prev = curr;
            }
            curr = curr.getNext();
        }
    }

    public void removeDuplicateValues() {
        if (size <= 1) return;
        Node prev = head;
        Node curr = head.getNext();
        while (curr != null) {
            if (prev.getData().equals(curr.getData())) {
                if (curr.getNext() != null) {
                    curr = curr.getNext();
                } else {
                    curr = curr.getNext();
                    prev.setNext(null);
                }
            } else {
                prev.setNext(curr);
                prev = curr;
                curr = curr.getNext();
            }
        }
    }

    public void removeRange(int min, int max) {
        if (head == null || (int) head.getData() > max) return;
        Node prev = null;
        Node next = null;
        Node curr = head;
        boolean lessHead = false;
        if ((int) head.getData() > min) {
            prev = head;
            lessHead = true;
        }
        while (curr != null) {
            int data = (int) curr.getData();
            if (!lessHead && data < min) {
                prev = curr;
            }
            if (data > max) {
                next = curr;
            }
            curr = curr.getNext();
        }
        if (prev != null) {
            if (prev == head && lessHead) {
                head = next;
            } else {
                prev.setNext(next);
            }
        }
    }

    public SinglyLinkedList intersection(SinglyLinkedList list) {
        SinglyLinkedList resultList = new SinglyLinkedList();
        Node aCurr = head;
        Node bCurr = list.head;
        while (aCurr != null && bCurr != null) {
            int a = (int) aCurr.getData();
            int b = (int) bCurr.getData();
            if (a < b) {
                resultList.add(aCurr.getData());
                aCurr = aCurr.getNext();
            } else if (a > b) {
                resultList.add(bCurr.getData());
                bCurr = bCurr.getNext();
            } else {
                resultList.add(aCurr.getData());
                aCurr = aCurr.getNext();
                bCurr = bCurr.getNext();
            }
        }
        while (aCurr != null) {
            resultList.add(aCurr.getData());
            aCurr = aCurr.getNext();
        }
        while (bCurr != null) {
            resultList.add(bCurr.getData());
            bCurr = bCurr.getNext();
        }
        return resultList;
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

    public static class Node {

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
