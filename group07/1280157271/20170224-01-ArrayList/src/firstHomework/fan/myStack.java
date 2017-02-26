package firstHomework.fan;

 

public class myStack {
	private myArrayList array = new myArrayList();//myArrayList对象里有个动态数组

	public void push(Object o){	//入栈
		 //长度不够时myArrayList会自己扩容
		array.add(o);//新对象放在数组后面
	}
	
	public Object pop(){//出栈，即数组尾上的元素， 还要删除他
		Object pop = array.get(array.size()-1);//下标要比size小1
		array.remove(array.size()-1);
		return pop;
	}
	
	public Object peek(){//只是弹出栈顶的值，不删除
		return array.get(array.size()-1);
	}
	public boolean isEmpty(){
		return array.size()==0;
	}
	public int size(){
		return array.size();
	}

	
}

