package linkedlist;

public class LinkedList implements List {
    private Node head;

    private int size;

    public void add(Object obj) {
        Node current = new
    }

    /**
     * 获取元素数量
     */
    public int size() {
        return size;
    }

    private static class Node {

        Object data;
        Node next;

    }
}
