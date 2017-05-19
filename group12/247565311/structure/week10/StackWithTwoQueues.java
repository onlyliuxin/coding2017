package structure.week10;

import structure.week1.Queue;

public class StackWithTwoQueues {
	private Queue<Integer> queue1;
	private Queue<Integer> queue2;
	public StackWithTwoQueues(){
		queue1 = new Queue<Integer>();
		queue2 = new Queue<Integer>();
	}
    public void push(int data) {       
        queue1.push(data);
    }
    public int peek(){
    	int val = 0;
    	while(queue1.size()>0){
    		val = queue1.pop();
    		queue2.push(val);
    	}
    	while(queue2.size()>0)
    		queue1.push(queue2.pop());
    	return val;
    }
    public int pop() {
       while(queue1.size()>1)
    	   queue2.push(queue1.pop());
       
       int val = queue1.pop();
       while(queue2.size()>0)
    	   queue1.push(queue2.pop());
       return val;
    }
}
