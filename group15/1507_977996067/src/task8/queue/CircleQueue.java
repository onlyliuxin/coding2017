package task8.queue;

@SuppressWarnings("unchecked")
public class CircleQueue<E> {

    private final static int DEFAULT_SIZE = 10;

    //用数组来保存循环队列的元素
    private Object[] elementData;

    private int size;
    //队头 读开始的位置
    private int front = 0;
    //队尾 写开始的位置
    private int rear = 0;

    public CircleQueue() {
        elementData = new Object[DEFAULT_SIZE];
        size = DEFAULT_SIZE;
    }

    public CircleQueue(int size) {
        this.size = size;
        elementData = new Object[size];
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public int size() {
        if (isEmpty()) {
            return 0;
        }
        return rear > front ? rear - front : size - (front - rear);
    }


    public void enQueue(E data) {
        int rearPosition = rear;
        elementData[rearPosition++] = data;
        rear = rearPosition % size;
    }

    public E deQueue() {
        if (isEmpty()) {
            return null;
        }
        int frontPosition = front;
        E dataToDeQueue = (E) elementData[frontPosition--];
        front = frontPosition < 0 ? frontPosition + size : frontPosition;
        return dataToDeQueue;
    }
}
