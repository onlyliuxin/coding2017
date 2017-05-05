package djj;
//栈
public class Stack {
	private ArrayList elementData = new ArrayList();
	private int pointer=0;
	
	public void push(Object o){
		elementData.add(o);
		pointer++;
	}
	
	public Object pop(){
		Object temp=elementData.remove(pointer);
		pointer--;
		return temp;
	}
	
	public Object peek(){
		return elementData.get(pointer);
	}
	public boolean isEmpty(){
		return pointer==0?true:false;
	}
	public int size(){
		return pointer;
	}
}
