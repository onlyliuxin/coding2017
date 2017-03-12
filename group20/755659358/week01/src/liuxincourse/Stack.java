package liuxincourse;

public class Stack {

	private LinkedList elementData=new LinkedList();
	
	public void push (Object o){
		elementData.addFirst(o);
	}
	
	public Object pop() {
		return elementData.removeFirst();
	}
	
	public Object peek(){
		return elementData.get(0);
	}
	
	public boolean isEmpty(){
		return size()==0?true:false;
	}
	
	public int size() {
		return elementData.size();
	}

}
