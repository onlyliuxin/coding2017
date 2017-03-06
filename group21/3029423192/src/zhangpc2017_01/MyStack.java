
public class MyStack {
	
	private MyArrayList elementData = new MyArrayList();
	private int size = 0;//记录栈中元素的个数
	
	//将元素o放入栈中
	public void push(Object o){
		elementData.add(o);
		size++;
	}
	
	
	//将最后放入栈元素弹出栈
	public Object pop(){
		size--;
		return elementData.remove(size);
	}
	
	//获取栈顶元素
	public Object peek(){
		return elementData.get(size);
	}
	
	//判断栈的元素是否为空
	public boolean isEmpty(){
		boolean flag;
		if(size == 0){
			flag = false;
		}
		flag = true;
		return flag;
	}
	
}

