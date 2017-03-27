package Collection;

public class Stack {
	private ArrayList elementData = new ArrayList();
	
	
	public void push(Object o){	
		elementData.add(o);
	}
	
	public Object pop(){
		int index=elementData.size()-1;
		Object data=elementData.get(index);
		elementData.remove(index);
		return data;
	}
	
	public Object peek(){
		
		int index=elementData.size()-1;
		return (Object)elementData.get(index);
	}
	public boolean isEmpty(){
		if(elementData.size()==0)
			return true;
		else
			return false;
	}
	
	public int size(){
		return elementData.size();
	}
}
