/**
 * Created by bdl19 on 2017/4/25.
 */
public class CircleQueue<E> {

    private final static int DEFAULT_SIZE = 10;

    //用数组来保存循环队列的元素
    private Object[] elementData = new Object[DEFAULT_SIZE];

    //队头
    private int front = 0;
    //队尾
    private int rear = 0;

    public boolean isEmpty() {
        return this.front == this.rear;
    }

    public int size() {
        int size = this.rear - this.front;
        return size < 0 ? size += DEFAULT_SIZE : size;

    }


    public void enQueue(E data) {
        if (this.isFull()) {
            System.out.println("队列已满");
            return;
        }
        elementData[this.rear] = data;
        this.rear = ++this.rear % DEFAULT_SIZE;
    }


    public E deQueue() {
        if (this.isEmpty()) {
            System.out.println("队列为空");
            return null;
        }
        E ele;
        ele = (E) elementData[this.front];
        this.front = (++this.front) % DEFAULT_SIZE;
        return ele;
    }

    public boolean isFull() {
        if ((this.rear + 1) % DEFAULT_SIZE == this.front) {
            return true;
        } else return false;
    }
}