package queue;

/**
 * Created by gongxun on 2017/4/24.
 * 用数组实现循环队列
 */
public class CircleQueue<E> {
    private final static int DEFAULT_SIZE = 10;

    //用数组来保存循环队列的元素
    private Object[] elementData = new Object[DEFAULT_SIZE] ;

    //队头
    private int front = 0;
    //队尾
    private int rear = 0;

    public boolean isEmpty() {
        return false;

    }

    public int size() {
        return -1;
    }



    public void enQueue(E data) {

    }

    public E deQueue() {
        return null;
    }
}
