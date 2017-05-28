package queue;

/**
 * @author jiaxun
 */
public class CircleQueue<E> {

    private Object[] elementData;

    private int front = 0;
    private int rear = 0;
    private int size = 0;

    public CircleQueue(int capacity) {
        elementData = new Object[capacity];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == elementData.length;
    }

    public int size() {
        return size;
    }

    public void enQueue(E data) {
        if (isEmpty()) {
            elementData[front] = data;
        } else {
            elementData[rear = (rear + 1) == elementData.length ? 0 : rear + 1] = data;
            if (rear == front) front = front + 1;
            if (front == elementData.length) front = 0;
        }
        size++;
    }

    public E deQueue() {
        if (isEmpty()) return null;
        E result = (E) elementData[front++];
        if (front == elementData.length) front = 0;
        size--;
        return result;
    }

}
