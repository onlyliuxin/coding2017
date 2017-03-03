/**
 * Created by spike on 2/19/17.
 */
public class LinkedList implements List {

    private LinkedListNode head;
    private LinkedListNode tail;
    private int size;

    private static class LinkedListNode {
        private Object data;
        private LinkedListNode prev;
        private LinkedListNode next;

        private LinkedListNode(Object data, LinkedListNode prev, LinkedListNode next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("List: [ ");
        LinkedListNode idx = head;
        while (idx != null) {
            builder.append(idx.data);
            builder.append(" ");
            idx = idx.next;
        }

        builder.append("]");
        return builder.toString();
    }

    public void add(int index, Object object) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException();
        }
        if (index == size) { // insert after
            add(object);
        } else {  // insert before
            LinkedListNode target = findNodeByIndex(index);
            LinkedListNode nd = new LinkedListNode(object, target.prev, target);
            if (head == target) {
                head = nd;
            } else {
                target.prev.next = nd;
            }
        }
        ++size;
    }

    public void add(Object object) {
        if (head == null) {
            LinkedListNode nd = new LinkedListNode(object, null, null);
            head = tail = nd;
        } else {
            LinkedListNode nd = new LinkedListNode(object, tail, null);
            tail.next = nd;
            tail = nd;
        }
        ++size;
    }

    public Object get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException();
        }
        LinkedListNode target = findNodeByIndex(index);
        return target.data;
    }

    public Object remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException();
        }
        LinkedListNode target = findNodeByIndex(index);
        if (target == head) {
            if (head == tail) {
                head = tail = null;
            } else {
                head = head.next;
                head.prev = null;
            }
        } else if (target == tail) {
            tail = tail.prev;
            tail.next = null;
        } else {
            target.prev.next = target.next;
            target.next.prev = target.prev;
        }
        target.prev = target.next = null;
        --size;
        return target.data;
    }

    private LinkedListNode findNodeByIndex(int index) {
        LinkedListNode target = head;
        for (int i = 0; i != index; ++i) {
            target = target.next;
        }
        return target;
    }

    public int size() {
        return size;
    }

    public Iterator iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator {

        LinkedListNode cursor = head;

        public boolean hasNext() {
            return cursor != null;
        }

        public Object next() {
            Object toRet = cursor.data;
            cursor = cursor.next;
            return toRet;
        }
    }
}
