package com.coding.basic;
/**
 * Created by wanc on 2017/2/21.
 * 利用ArrayList 实现栈
 */
public class Stack {
    /**
     * 利用ArrayList 保存数据
     */
    private ArrayList elementData = new ArrayList();

    /**
     * 入栈
     * @param o
     */
    public void push(Object o) {
        elementData.add(o);
    }

    /**
     * 出栈
     * @return
     */
    public Object pop() {
        elementData.remove(elementData.size()-1);
        return null;
    }

    /**
     * 返回栈顶数据
     * @return
     */
    public Object peek() {
        return elementData.get(elementData.size()-1);
    }

    /**
     * 是否为空
     * @return
     */
    public boolean isEmpty() {
        return elementData.size()==0?true:false;
    }

    /**
     * 返回栈长度
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
        return "Stack{" +
                "elementData=" + elementData +
                '}';
    }
}
