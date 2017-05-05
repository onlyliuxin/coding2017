package cn.task1;

public class Stack {
	
	Object[] obj;
	final static int capacity = 100;
	int top;
	
	public Stack(){
		obj = new Object[capacity];
		this.top = -1;
	}
	
	public Object push(Object object){
		if(top<-1){
			try {
				throw new Exception("数组越界！");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			obj[top+1] = object;
			top++;			
		}
		return obj[top];
	}
	
	public Object peek(){
		return obj[top];
	}
	
	public Object pop(){
		Object temp = obj[top];
		obj[top] = null;
		top--;
		return temp;
	}
	
	public int size(){
		return top+1;
	}
	
	public boolean isEmpty(){
		return !(top>-1);
	}
	
	public int search(Object obj){
		
		return -1;
	}
	
	public static void main(String[] args) {
		
		Stack s = new Stack();
		
		s.push("1");
		s.push("2");
		s.push("12");
		s.push("3");
		s.push("34");
		
		System.out.println(s.pop()+"   "+s.isEmpty());
		
		System.out.println(s.size()+"   "+s.peek());
		
	}
}
