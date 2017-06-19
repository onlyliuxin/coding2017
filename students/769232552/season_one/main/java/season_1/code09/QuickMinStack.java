package code09;

import code05.Stack;

/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回改数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1)，简单来讲，操作一次就可以得到最小值
 *
 * 构造一个辅助栈，每次压入当前主栈中最小的元素
 */
public class QuickMinStack {

    Stack mainStack = new Stack();
    Stack subStack = new Stack(); //辅助栈

    public void push(int data){
        mainStack.push(data);

        //每次压入当前主栈中最小的元素
        if(subStack.isEmpty()){
            subStack.push(data);
        }
        else if(data >= (Integer)subStack.peek()){
            subStack.push(subStack.peek());
        }
        else {
            subStack.push(data);
        }

    }

    public int pop(){
        subStack.pop();
        return (Integer) mainStack.pop();
    }

    public int findMin(){
        return (Integer) subStack.peek();
    }
}