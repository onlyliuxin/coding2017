package com.circle.collection;

/**
 * Created by keweiyang on 2017/2/25.
 * 自定义Stack，此Stack是基于ArrayList实现的
 */
public class Stack {

    private ArrayList elementData = new ArrayList(4);

    /**
     * 入栈
     *
     * @param o
     */
    public void push(Object o) {

        elementData.add(o);
    }

    /**
     * 出栈
     *
     * @return
     */
    public Object pop() {
        Object ret = null;
        if (elementData.size() > 0) {
            ret = elementData.remove(elementData.size() - 1);
        }else{
            throw new RuntimeException("栈中没有元素");
        }
        return ret;
    }

    /**
     * 获取栈顶元素
     *
     * @return
     */
    public Object peek() {
        Object ret = null;
        if (elementData.size() > 0) {
            ret = elementData.get(elementData.size() - 1);
        } else {
            throw new RuntimeException("栈中没有元素");
        }
        return ret;
    }

    public boolean isEmpty() {
        return elementData.size() <= 0;
    }

    public int size() {
        return elementData.size();
    }
}
