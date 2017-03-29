
//package javaTest;

public class Stack {
   private ArrayList elementData = new ArrayList();
	
	//将元素压入栈
	public void push(Object o){	
		elementData.add(o);
	}
	//将元素出栈
	public Object pop(){
		Object o=elementData.remove(elementData.size-1);
		return o;
	}
	//获取栈顶元素
	public Object peek(){
		Object o=elementData.get(elementData.size-1);
		return o;
	}
	//判断栈是否为空
	public boolean isEmpty(){
		boolean flag=true;
		if(elementData.size>0)
		{
			flag=false;
		}
		return flag;
	}
	//获取栈的大小
	public int size(){
		return elementData.size;
	}
}

