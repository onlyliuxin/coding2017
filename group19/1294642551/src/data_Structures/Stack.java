package data_Structures;

public class Stack {
//	private ArrayList elementData = new ArrayList();
	
	private LinkedList ll;
	Stack()
	{
		ll = new LinkedList();
	}
	
	public void push(Object o){
		ll.addLast(o);
	}
	
	public Object pop(){
		return ll.removeLast();
	}
	
	public Object peek(){
		return null;
	}
	public boolean isEmpty(){
		return ll.size()==0;
	}
	public int size(){
		return ll.size();
	}
}
