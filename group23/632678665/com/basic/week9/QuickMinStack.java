

import java.util.Stack;

/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回改数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 * @author chk
 *
 */
public class QuickMinStack extends Stack<Integer> {

    Stack<Integer> sMin;
    public QuickMinStack(){
        sMin = new Stack<Integer>();
    }
    public void push(int data){
        if(data <= findMin()){
            sMin.push(data);
        }
        super.push(data);
    }
    public Integer pop(){
        int value = super.pop();
        if(value == findMin()){
            sMin.pop();
        }
        return value;
    }
    public int findMin(){
        if(sMin.isEmpty()){
            return Integer.MAX_VALUE;
        }
        return sMin.peek();
    }

    public static void main(String args[]){
        QuickMinStack stack = new QuickMinStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(0);
        System.out.println(stack.pop().intValue());
        System.out.println(stack.findMin());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
