package com.coding.basic;

/**
 * Created by huitailang on 17/2/25.
 * @author zhangkun
 * @date 2017年02月25日17:34:22
 */
public class Stack {
    private ArrayList elementData = new ArrayList();

    public void push(Object o){
        elementData.add(o);
    }

    public Object pop(){
        Object o = elementData.get(1);
        elementData.remove(1);
        return o;
    }

    public Object peek(){
        return elementData.get(1);
    }

    public boolean isEmpty(){
        return elementData.size() != 0;
    }

    public int size(){
        return elementData.size();
    }
}
