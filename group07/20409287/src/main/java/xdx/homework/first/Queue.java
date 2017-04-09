package xdx.homework.first;

/**
 * @Description: 链式队列
 */
public class Queue<E> {

    private class Node {

        private E data; // 数据域

        private Node next;  // 指针域

        public Node() {
        }

        private Node(E data) {
            this.data = data;
            this.next = null;
        }

    }

    private Node front;    // 队头

    private Node rear;     // 队尾

    private int length; // 队长

    public void enQueue(E data) {

        if(this.front == null) {
            this.front = new Node(data);
            this.rear = this.front;
        } else {
            Node newNode = new Node(data);
            this.rear.next = newNode;
            this.rear = newNode;
        }
        length++;
    }

    public E deQueue() {

        if (isEmpty()) {
            return null;
        }
        Node oldFront = this.front;
        this.front = this.front.next;
        length--;
        return oldFront.data;
    }

    public int length() {
        return length;
    }

    public boolean isEmpty() {
        return this.front == this.rear;
    }

    public void clear() {
        this.length = 0;
        this.front = null;
        this.rear = null;
    }

    public E getFront() {

        if (this.isEmpty()) {
            return null;
        }
        return this.front.data;
    }

    public E getRear() {

        if(this.isEmpty()) return null;
        return this.rear.data;
    }

    public String toString() {

        if (this.length == 0) return "[]";

        Node node = this.front;
        StringBuilder queueToString = new StringBuilder("[");
        while (node.next != null) {
            queueToString.append(node.data.toString()).append(",");
            node = node.next;
        }
        queueToString.append(node.data.toString()).append("]");
        return queueToString.toString();
    }


}
