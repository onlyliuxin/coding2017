package cn.fyl.first;

public class Queue {

	LinkedList linkedlist = new LinkedList();
	
	public void enQueue(Object o){		
		linkedlist.addLast(o);
	}
	
	public Object deQueue(){
		return linkedlist.removeFirst();
	}
	
	public boolean isEmpty(){
		if(linkedlist.size()>0)
		return false;
		else
		return true; 
	}
	
	public int size(){
		return linkedlist.size();
	}
	
	public Object get(int index){
		return linkedlist.get(index);
	}
	
	public static void main(String[] arg){
		Queue q = new Queue();
		q.enQueue(1);
		q.enQueue(2);
		System.out.println(q.get(1));
	} 
	
}
