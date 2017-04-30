package algorithm.queue;

import datastructure.exception.EmptyQueueException;

/**
 * 用数组实现循环队列
 * @author liuxin
 *
 * @param <E>
 */
public class CircleQueue<E> {
	private final static int DEFAULT_SIZE = 10;

    //数组实现自增长的循环队列
    private Object[] array = new Object[DEFAULT_SIZE];
    private int head = 0;
    private int rear = 0;

    public CircleQueue() {}

    public CircleQueue(int initCapacity) {
        this.array = new Object[initCapacity];
    }

    public void enQueue(E o) {
        int target = mapIndex(rear);
        autoGrow();
        array[target] = o;
        rear++;
    }

    public E deQueue() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        Object obj = array[mapIndex(head)];
        head++;
        @SuppressWarnings("unchecked") E e = (E) obj;
        return e;
    }

    public boolean isEmpty() {
        return head == rear;
    }

    public int size() {
        return rear - head;
    }

    private int capacity() {
        return array.length;
    }

    private void autoGrow() {
        if (size() >= capacity()) {
            Object[] newArray = new Object[nextCapacity()];
            System.arraycopy(array, 0, newArray, 0, capacity());

            int increase = nextCapacity() - capacity();
            int moveCount = size() - mapIndex(rear);
            System.arraycopy(newArray, mapIndex(head), newArray, mapIndex(head) + increase, moveCount);
            array = newArray;
            head += increase;
            rear += increase;
        }
    }

    private int nextCapacity() {
        return capacity() * 2;
    }

    private int mapIndex(int index) {
        return index % capacity();
    }
}
