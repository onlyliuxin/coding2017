package src;

/**
 * Created by Yang on 2/25/2017.
 */
public class MyLinkedList implements MyList {

    private int size = 0;

    private Node header;

    @Override
    public void add(Object obj) {
        Node newNode = new Node(obj);
        newNode.next = header;
        header = newNode;
        size++;
    }

    @Override
    public void add(int index, Object obj) {
        this.validIndex(index);
        Node current = this.getCurrentNode(index);
        Node newNode = new Node(obj);
        newNode.next = current.next;
        current.next = newNode;
        size++;
    }

    @Override
    public Object get(int index) {
        this.validIndex(index);
        Node node = getCurrentNode(index);
        return node.data;
    }

    @Override
    public Object remove(int index) {
        this.validIndex(index);

        if(index == 0) return removeFirst();
        if(index == size -1) return removeLast();

        Node node = getCurrentNode(index+1);
        node.next = node.next.next;
        size--;
        return node.data;
    }

    @Override
    public int size() {
        return size;
    }

    private static class Node {
        Object data;
        Node next;
        public Node(){
        }
        public Node(Object data) {
            this.data = data;
        }
    }

    private void validIndex(int inputIndex) {
        if(inputIndex > size || inputIndex < 0){
            throw new RuntimeException("Index: " + inputIndex + " out of bounds( " + size +" )");
        }
    }

    private Node getCurrentNode(int index) {
        Node current = header;
        for(int i = 0; i < size-index -1; i++){
            current = current.next;
        }
        return current;
    }

    public Object removeFirst() {
        Node temp = new Node();
        temp.next = header;
        temp.next = header.next;
        return temp.next;
    }

    private Object removeLast() {
        Node preNode = new Node();
        while (preNode.next.next != null) {
            preNode = preNode.next;
        }
        Node lastNode = preNode.next.next;
        preNode.next = null;
        return lastNode;
    }

    public void addLast(Object o){
        Node preNode = new Node();
        while (preNode.next != null){
            preNode = preNode.next;
        }
        Node lastNode = new Node(o);
        preNode.next = lastNode;
    }

    public boolean isEmpty() {
        if (size() == 0){
            return false;
        }
        return true;
    }
}
