package week1.collection;

/**
 * Created by zndbl on 2017/3/11.
 */
public class MyLinkedList {

    private int size;
    private Node first;
    private Node last;

    public static class Node{
        Object item;
        Node next;
        Node prev;

        public Node(Object item, Node next, Node prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    public boolean add(Object element) {
        addAtLast(element);
        return true;
    }

    public void addAtLast(Object element) {
        Node l = last;
        Node node = new Node(element,null,l);
        last = node;
        if(l == null) {
            first = node;
        } else {
            l.next = node;
        }
        size++;
    }

    public Node node(int index) {
        if(index < size/2 ) {
            Node cussor = first;
            for (int i = 0; i < index ; i++) {
                cussor = cussor.next;
            }
            return cussor;
        } else {
            Node cussor = last;
            for (int i = size -1 ; i > index ; i--) {
                cussor = cussor.prev;
            }
            return cussor;
        }
    }

    public Object get(int index) {
        checkRange(index);
        return node(index).item;
    }

    public void checkRange(int index) {
        if(index >= size || index < 0) {
            throw new RuntimeException("index超过界限");
        }
    }

    public int indexOf(Object element) {
        Node cussor = first;
        int count = 0;
        while (cussor != null) {
            if(element.equals(cussor.item)) {
                return count;
            }
            count++;
            cussor = cussor.next;
        }
        return -1;
    }

    public boolean remove(Object o) {
        int index = indexOf(o);
        if(index < 0) {
            return false;
        }
        deleteLink(index);
        return true;
    }

    public Object deleteLink(int index) {
        Node l = node(index);
        Object item = l.item;
        Node prevNode = l.prev;
        Node nextNode = l.next;

        if(prevNode == null) {
            first = nextNode;
        } else {
            prevNode.next = nextNode;
            l.next = null;
        }
        if(nextNode == null) {
            last = prevNode;
        } else {
            nextNode.prev = prevNode;
            l.prev = null;
        }
        size--;
        l.item = null;
        return item;

    }


}
