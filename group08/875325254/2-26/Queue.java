import java.util.Arrays;

/**
 * Created by Great on 2017/2/23.
 */
public class Queue {
    private class Node{
        Node next;
        int data;
    }
    private int size;
    private Node front;
    private Node rear;
    public void add(int e) {
        if (front == null) {
            front = new Node();
            rear = front;
        }else {
            front.next = new Node();
            front = front.next;
        }
        front.data = e;
        ++size;
    }

    public Integer poll() {
        if (rear == null) return null;
        int data = rear.data;
        rear = rear.next;
        --size;
        return data;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}
