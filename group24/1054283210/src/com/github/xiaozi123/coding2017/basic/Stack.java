package com.github.xiaozi123.coding2017.basic;

public class Stack {
	private ArrayList elementData = new ArrayList();
	
	/*
	 * ��ջ
	 */
	public void push(Object o){
		elementData.add(o);
	}
	
	public Object pop(){
		return elementData.remove(elementData.size()-1);
	}
	
	public Object peek(){
		return elementData.get(elementData.size()-1);
	}
	public boolean isEmpty(){
		return elementData.size()==0;
	}
	public int size(){
		return elementData.size();
	}
	
	public static void main(String[] args) {
		Stack stack=new Stack();
		if (stack.isEmpty()) {
			System.out.println("stackΪ�ա�");
		}
		int n=3;
		for (int i = 0; i < n; i++) {
			stack.push(i);
		}
		System.out.println("stack���ڳߴ�Ӧ��Ϊ3:"+(stack.size()==3));
		System.out.println("stack��������Ӧ��Ϊ��2,1,0.");
		for (int i = 0; i < n; i++) {
			System.out.println(stack.pop());
		}
//		for (int i = 0; i < n; i++) {
//			stack.peek();
//		}
//		
		
		
		
	}
	
	
}
