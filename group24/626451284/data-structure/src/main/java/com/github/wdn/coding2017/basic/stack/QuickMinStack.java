package com.github.wdn.coding2017.basic.stack;

import java.util.*;

/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回改数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 * Created by Administrator on 2017/5/6 0006.
 */
public class QuickMinStack<E extends Comparable> {
    private Stack<E> stack = new Stack<>();
    private Map<Integer, E> minIndexMap = new HashMap<>();
    private List<Integer> minIndexs = new ArrayList<>();

    public static void main(String[] args) {
        QuickMinStack quickMinStack = new QuickMinStack();
        //int[] ints = {5,4,3,2,1,0};
        int[] ints = {4,1,2,5,3,0};
        for (int i = 0; i < ints.length; i++) {
            quickMinStack.push(ints[i]);
        }
        quickMinStack.status();
        while (!quickMinStack.isEmpty()){
            System.out.print("min:"+quickMinStack.findMin());
            System.out.println(" pop:"+quickMinStack.pop());
        }
    }
    public void push(E data){
        stack.push(data);
        if(minIndexs.size()==0){
            minIndexMap.put(0,data);
            minIndexs.add(0);
        }else {
            if(findMin().compareTo(data)>0){
                minIndexMap.put(stack.size()-1,data);
                minIndexs.add(stack.size()-1);
            }
        }
    }
    public E pop(){
        E min = findMin();
        E result = stack.peek();
        if(min==result){
            minIndexMap.remove(minIndexs.get(minIndexs.size()-1));
            minIndexs.remove(minIndexs.size()-1);
        }
        return stack.pop();
    }
    public E findMin(){
        return minIndexMap.get(minIndexs.get(minIndexs.size()-1));
    }
    public boolean isEmpty(){
        return stack.isEmpty();
    }
    public void status(){
        System.out.println(stack);
        System.out.println(minIndexs);
        System.out.println(minIndexMap);
    }
}
