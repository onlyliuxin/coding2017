package com.zzk.coding2017.zuoye_1;

public class Queue {
	ArrayList queue;
	public Queue() {
		// TODO Auto-generated constructor stub
		queue = new ArrayList();
	}
	public void enQueue(Object o){	
		queue.add(o);
	}
	
	public Object deQueue(){
		if(queue.size()==0){
			return null;
		}else{
			Object result = queue.get(0);
			queue.remove(0);	
			return result;
		}
	}
	
	public boolean isEmpty(){
		if(queue.size()==0)
			return true;
		else
			return false;
	}
	
	public int size(){
		return queue.size();
	}
}
