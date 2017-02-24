package com.coding.basic;

/**
 * Created by wanc on 2017/2/21.
 * 利用LinkedList 实现队列
 */
public class Queue {
    /**
     * 利用LinkedList 保存数据
     */
    private LinkedList elementData = new LinkedList();

    /**
     * 入队
     *
     * @param o
     */
    public void enQueue(Object o) {
        elementData.add(o);
    }

    /**
     * 出队
     *
     * @return
     */
    public Object deQueue() {
        return elementData.removeFirst();
    }

    /**
     * 是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return elementData.size() == 0 ? true : false;
    }

    /**
     * 返回队列长度
     *
     * @return
     */
    public int size() {
        return elementData.size();
    }

    /**
     * 重写toString 方便打印
     *
     * @return
     */
    @Override
    public String toString() {
        return "Queue{" +
                "elementData=" + elementData +
                '}';
    }
}
