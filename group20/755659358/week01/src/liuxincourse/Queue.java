package liuxincourse;

public class Queue {
	
	LinkedList list=new LinkedList();
	
	public void enQueue(Object o){
		list.add(o);
	}
	
	public Object deQueue(){
	
		return list.removeFirst();
	}
	
	public boolean isEmpty() {
		
		return size()==0?true:false;
	}
	
	public int size(){
		return list.size();
	}

}
