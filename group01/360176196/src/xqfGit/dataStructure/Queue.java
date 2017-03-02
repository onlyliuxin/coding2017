package xqfGit.dataStructure;
import java.util.LinkedList;

public class Queue {
	LinkedList list = new LinkedList();
	
	public void enQueue(Object o){
		list.addLast(o);
	}
	
	public Object deQueue(){
		Object o =list.getFirst();
		list.removeFirst();
		return o;
	}
	
	public boolean isEmpty(){
		if(list.size() == 0){
			return true;
		}
		return false;
	}
	
	public int size(){
		return list.size();
	}
}
