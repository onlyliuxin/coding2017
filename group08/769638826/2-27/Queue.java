package com.coding.basic;

/**
 * Created by huitailang on 17/2/25.
 * @author zhangkun
 * @date 2017年02月25日17:40:02
 */
public class Queue {
    private ArrayList elementData = new ArrayList();

    public void enQueue(Object o){
        elementData.add(o);
    }

    public Object deQueue(){
        return elementData.get(size());
    }

    public boolean isEmpty(){
        return elementData.size() != 0;
    }

    public int size(){
        return elementData.size();
    }
}
