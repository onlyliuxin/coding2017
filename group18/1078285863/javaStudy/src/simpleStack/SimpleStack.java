package simpleStack;

import java.util.ArrayList;

public class SimpleStack {
private ArrayList elementData = new ArrayList();
	
	public void push(Object o){	
		elementData.add(o);
	}
	
	public Object pop(){
		//判断队列是否为空
		Object obj = peek();
		if(obj != null)
		{
			elementData.remove(obj);
			return obj;
		}
		else {
			return null;
		}
	}
	
	public Object peek(){
		if(elementData.isEmpty()){
			return null;
		}
		else {
			int lastIndex = elementData.size() -1;
			Object obj = elementData.get(lastIndex);
			return obj;
		}
		
	}
	public boolean isEmpty(){
		boolean bEmpty = false;
		bEmpty = elementData.isEmpty()?true:false;
		return bEmpty;
	}
	public int size(){
		return elementData.size();
	}
}
