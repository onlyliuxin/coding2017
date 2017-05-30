package main.coding_170507;


import java.util.ArrayList;

/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回改数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 */
public class QuickMinStack {
    private ArrayList<Integer> stack = new ArrayList<>();
    private ArrayList<Integer> minStack= new ArrayList<>();
    public void push(int data) {
        stack.add(data);
        if(minStack.size()==0){
            minStack.add(data);
        }else{
            if(minStack.get(minStack.size()-1)>data){
                minStack.add(data);
            }
        }
    }

    public int pop() {
        int data = stack.remove(stack.size()-1);
        if(data==minStack.get(minStack.size()-1)){
            minStack.remove(minStack.size()-1);
        }
        return data;
    }

    public int findMin() {
        return minStack.get(minStack.size()-1);
    }
}
