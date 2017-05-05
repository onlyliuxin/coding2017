package week1.collection;

/**
 * Created by zndbl on 2017/3/12.
 */
public class MyQueue {

    private Object[] data;
    private int head;
    private int tail;

    public MyQueue() {
        data =  new Object[10];
        head = 1;
        tail = 1;
    }

    public void put(Object obj) {
        data[tail] = obj;
        tail++;
    }

    public Object get() {
        Object obj = data[head];
        head++;
        return obj;
    }

}
