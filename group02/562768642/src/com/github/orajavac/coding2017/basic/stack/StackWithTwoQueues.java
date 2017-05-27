package com.github.orajavac.coding2017.basic.stack;


/**
 * （1）入栈：Q1：1，2，3，4入队列（相当于入栈）；
 （2）出栈：Q1：1，2，3出队列，Q2：1，2，3入队列；（此时Q1只剩4，正是我们要出栈的元素）
 （3）
     1>入栈：Q1：5入队列（每次入栈都用Q1入队列实现，而不入队列Q2，这样会提高效率，后面会说到）
     2>出栈：判断：如果Q1队列不为空就用（1）（2）步的方法出栈最后一个元素。否则，出栈Q2队列的最后一个元素。
 * @author ora
 *
 */
public class StackWithTwoQueues {
	
	private Stack q1 = new Stack();
	
	private Stack q2 = new Stack();
	
	public void push(int data) {
		q1.push(data);
    }

    public int pop() {
    	int len = q1.length();
    	while(len != 1){
    		q2.push(q1.popLow());
    		len--;
    	}
    	int result = Integer.parseInt(q1.popLow().toString());
    	
    	//再push回去
    	len = q2.length();
    	while(len != 0){
    		q1.push(q2.popLow());
    		len--;
    	}
    	return result;
    }
    
    //测试使用，观察栈里元素
    public String toString(){
    	System.out.println(q1.positiveToString());
    	return q1.positiveToString();
    }
}
