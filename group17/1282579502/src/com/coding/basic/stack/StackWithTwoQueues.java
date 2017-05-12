package com.coding.basic.stack;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class StackWithTwoQueues {
	
	Queue<Integer> q1 = new LinkedList<>();
	Queue<Integer> q2 = new LinkedList<>();

	Queue<Integer> qIn = q1;
	//Queue<Integer> qOut = q2;
    public void push(int data) {       
    	qIn.add(data);
    }

    public int pop() {
    	Queue<Integer> qOut =null;
       if(q1.isEmpty()){
    	   qOut = q1;
       }
       else{
    	   qOut = q2;
       }
    	
       for(int i = 0 ; i< qIn.size() -1 ; i++){
    	  qOut.add(qIn.poll());
       }
       int item = qIn.poll().intValue();
       qIn = qOut;
       return item;
    }
    
    public void dump(){
    	Iterator<Integer> iter1 = q1.iterator();
    	while(iter1.hasNext()){
    		System.out.print(iter1.next() + " ");
    	}
    	System.out.println();
    	
    	Iterator<Integer> iter2 = q2.iterator();
    	while(iter2.hasNext()){
    		System.out.print(iter2.next() + " ");
    	}
    	System.out.println();
    }

    
}