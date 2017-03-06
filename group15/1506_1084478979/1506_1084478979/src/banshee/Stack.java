package banshee;

public class Stack {
	private ArrayList elementData = new ArrayList();
	
	public void push(Object o){	
		elementData.add(o);
	}
	
	public Object pop(){
		return elementData.remove(elementData.size() - 1);
	}
	
	public Object peek(){
		return  elementData.get(elementData.size()-1);
	}
	public boolean isEmpty(){
		return elementData.size()==0?true:false;
	}
	public int size(){
		return elementData.size();
	}
}
