package io.github.vxzh;

/**
 * Created by vxzh on 24/02/2017.
 */
public class LinkQueue implements Queue {

    private int size = 0;

    private Node front;
    private Node rear;

    /**
     * 入队
     */
    public void enQueue(Object o) {
        Node node = rear;
        Node newNode = new Node(o, null);
        rear = newNode;
        if (node == null)
            front = newNode;
        else
            node.next = newNode;
        size++;
    }

    /**
     * 出队
     */
    public Object deQueue() {
        if (isEmpty())
            throw new RuntimeException("EmptyQueueException");
        Node node = front;
        front = node.next;
        size--;
        return node.data;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private static class Node {
        Object data;
        Node next;

        Node(Object element, Node next) {
            this.data = element;
            this.next = next;
        }

    }
}
