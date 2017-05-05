package basic;

public class Stack {
	private ArrayList elementData = new ArrayList();
	
	public void push(Object o){		
		elementData.add(o);
	}
	
	public Object pop(){
		if (elementData.size()==0 )
		{
			return null;
		}
		Object tempObj = elementData.remove(elementData.size()-1);		
			
		return tempObj;
	}
	
	public Object peek(){
		if (elementData.size()==0 )
		{
			return null;
		}
		Object tempObj = elementData.get(elementData.size()-1);		
		return tempObj;
	}
	public boolean isEmpty(){
		if (elementData.size()==0 )
		{
			return true;
		}
		else {

			return false;
		}
	}
	public int size(){
		return elementData.size();
	}
}
