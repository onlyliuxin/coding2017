package assignment0416.queue;

/**
 * 用数组实现循环队列
 *
 * @param <E>
 * @author liuxin
 */
public class CircleQueue<E> {

    private final static int DEFAULT_SIZE = 10;

    //用数组来保存循环队列的元素
    private Object[] elementData = new Object[DEFAULT_SIZE];

    //队头
    private int front = 0;
    //队尾
    private int rear = 0;

    public CircleQueue() {

    }

    public CircleQueue(int size) {
        this.elementData = new Object[size];
    }

    public boolean isEmpty() {
        return front == rear && elementData[front] == null;
    }

    public int size() {
        return rear - front >= 0 ? rear - front + 1 : elementData.length - front + rear + 1;
    }


    public void enQueue(E data) {
        if (isFull()) {
            throw new FullQueueException();
        }
        if (isEmpty()) {
            elementData[rear] = data;
        } else {
            rear = ++rear % elementData.length;
            elementData[rear] = data;
        }
    }

    public E deQueue() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }

        Object data = elementData[front];
        elementData[front] = null;
        if (front != rear) {
            front = ++front % elementData.length;
        }
        return (E) data;
    }

    public boolean isFull() {
        return front - rear == 1 || rear - front == elementData.length - 1;
    }

    @Override
    public String toString() {
        if (isEmpty())
            return "[]";
        StringBuilder stringBuilder = new StringBuilder("size:" + size() + "-front:" + front + "-rear:" + rear);
        stringBuilder.append('[');
        for (int i = front; i < front + size(); i++) {
            stringBuilder.append(elementData[i % elementData.length] + ", ");
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    private static class FullQueueException extends RuntimeException {
        public FullQueueException() {
            super();
        }

        public FullQueueException(String message) {
            super(message);
        }
    }

    private static class EmptyQueueException extends RuntimeException {
        public EmptyQueueException() {
            super();
        }

        public EmptyQueueException(String message) {
            super(message);
        }
    }
}
