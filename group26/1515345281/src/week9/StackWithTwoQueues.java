package week9;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 使用两个队列实现一个栈
 */
public class StackWithTwoQueues {
	
	//使用双端队列实现比LinkedList性能好
	private Queue<Integer> queueOne=new ArrayDeque<Integer>();
	private Queue<Integer> queueTwo=new ArrayDeque<Integer>();
    
    public void push(int data) {
      	
    	queueOne.add(data);
    }

    public int pop(){
    	
       if(queueOne.isEmpty() && queueTwo.isEmpty()){
    	   throw new RuntimeException("The queue is Empty,can't excute pop");
       }
       
       if(!queueOne.isEmpty()){
    	   
	       while(queueOne.size() > 1){
	    	   
	    	   queueTwo.add(queueOne.poll());
	   
	       }
	       return queueOne.poll();
       }
       
       while(queueTwo.size() >1){
    	 
    		queueOne.add(queueTwo.poll());
       }
       
       return queueTwo.poll();
    }
}