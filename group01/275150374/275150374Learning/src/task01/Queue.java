package task01;

import java.util.Arrays;

/**第一周作业
 * 自己实现一个 队列
 * Created by eurry on 2017/2/26.
 */
public class Queue {

    private int size=0;
    private Object[] elementData = {};

    /**
     * 入队列
     */
    public void enQueue(Object o){
        elementData = Arrays.copyOf(elementData, size+1);
        elementData[size] = o;
        size++;
    }

    /**
     * 出队列
     */
    public Object deQueue(){
        Object result = elementData[0];
        Object[] newData = new Object[size-1];
        System.arraycopy(elementData, 1, newData, 0,size-1);
        elementData = newData;
        size--;
        return result;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public int size(){
        return size;
    }
}
