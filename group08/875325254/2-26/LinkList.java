/**
 * Created by Great on 2017/2/24.
 */
public class LinkList {
    private class Node{
        Node next;
        int data;
    }
    private Node rear;
    private Node top;
    private int size = 0;
    public void add(int e) {
        if (rear == null) {
            rear = new Node();
            top = rear;
        }else {
            rear.next = new Node();
            rear = rear.next;
        }
        rear.data = e;
        ++size;
    }

    public Integer getReciprocal(int count) {
        if (top == null || count < 1) return null;
        Node node = top;
        int size = 0;
        while( node.next != null) {
            node = node.next;
            size++;
        }
        if (size+1 < count) return null;
        return get(size+1-count);
    }

    public Integer get(int i) {
        if (i < 0 || i >= size) return null;
        return getNode(i).data;
    }

    public void remove(int i) {
        if (i < 0 || i >= size) return;
        if (i == 0 ) {
            top = top.next;
            return;
        }
        if (i == size - 1) {
            getNode(i - 1).next = null;
        }
        Node front = getNode(i - 1);
        Node back = getNode(i + 1);
        front.next = back;
        --size;
    }

    private Node getNode(int i) {
        if (i < 0 || i >= size) return null;
        Node node = top;
        for (int j = 0; j < size; j++) {
            if (j == i) return node;
            node = node.next;
        }
        return null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
