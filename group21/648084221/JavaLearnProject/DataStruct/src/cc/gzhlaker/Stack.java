package cc.gzhlaker;

public class Stack extends ArrayList  {
	LinkList top;
	int size;
	public  Stack(){
		top = new LinkList();
		size = 0;
	}
	public void push(int number){
		top.add(number);
		size++;
	}
	
	public void pop(){
		top.removeEnd();
		size++;
	}
	
	public void peek(){
		int data = top.getData(size);
		System.out.println(data);
	}

}


