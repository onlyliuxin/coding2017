package task1;

/**
 * Queue 实现
 */
public class MyQueue<T> {
    private MyLinkedList<T> elementData = new MyLinkedList<T>();

    public void enQueue(T o) {
        elementData.addFirst(o);
    }

    public T deQueue() {
        return elementData.removeLast();
    }

    public boolean isEmpty() {
        return elementData.size() == 0;
    }

    public int size() {
        return elementData.size();
    }

}
