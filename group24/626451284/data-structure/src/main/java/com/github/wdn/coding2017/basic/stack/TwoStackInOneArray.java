package com.github.wdn.coding2017.basic.stack;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 用一个数组实现两个栈
 * 将数组的起始位置看作是第一个栈的栈底，将数组的尾部看作第二个栈的栈底，
 * 压栈时，栈顶指针分别向中间移动，直到两栈顶指针相遇，则扩容。
 * Created by Administrator on 2017/5/6 0006.
 */
public class TwoStackInOneArray {
    private Object[] ints = new Object[10];
    private int head1 = -1;
    private int head2 = ints.length;

    public static void main(String[] args) {
        TwoStackInOneArray stack = new TwoStackInOneArray();
        for (int i = 11; i > 0; i--) {
            stack.push2(i);
        }
        for (int i = 0; i < 8; i++) {
            stack.push1(i);
        }
        stack.pop1();
        for (int i = 0; i < 4; i++) {
            stack.push1(i);
        }

        System.out.println(Arrays.toString(stack.ints));
    }
    public void push1(Object data){
        if(head1+1<ints.length && ints[head1+1]==null){
            ints[++head1] = data;
        }else{
            grow();
            ints[++head1] = data;
        }
    }
    public void push2(Object data){
        if(head2-1>=0 && ints[head2-1]==null){
            ints[--head2] = data;
        }else{
            grow();
            ints[--head2] = data;
        }
    }
    public Object pop1(){
        if(head1<0){
            throw new RuntimeException("stack1 is empty");
        }
        Object result = ints[head1];
        ints[head1] = null;
        head1--;
        return result;
    }
    public Object pop2(){
        if(head2>=ints.length){
            throw new RuntimeException("stack2 is empty");
        }
        Object result = ints[head2];
        ints[head2] = null;
        head2++;
        return result;
    }
    public void grow(){
        Object[] newArr = new Object[ints.length * 2];
        System.arraycopy(ints,0,newArr,0,head1+1);
        System.arraycopy(ints, head2, newArr, newArr.length-(ints.length-head2), ints.length-head2);
        head2 = newArr.length-(ints.length-head2);
        ints = newArr;
    }
}
