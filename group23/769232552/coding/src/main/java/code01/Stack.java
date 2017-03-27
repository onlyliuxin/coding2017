package code01;

public class Stack {
	private ArrayList elementData = new ArrayList();
	
	public void push(Object o){
		elementData.add(o);
	}
	
	public Object pop(){
		Object result = null;
		if(elementData.size()!=0) {
			result = elementData.remove(elementData.size() - 1);
		}
		return result;
	}
	
	public Object peek(){
		Object result = null;
		if(elementData.size()!=0) {
			result = elementData.get(elementData.size() - 1);
		}
		return result;
	}
	public boolean isEmpty(){
		return elementData.size() == 0;
	}
	public int size(){
		return elementData.size();
	}
}
