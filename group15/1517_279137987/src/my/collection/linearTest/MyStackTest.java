package my.collection.linearTest;

import my.collection.linear.MyStack;

public class MyStackTest {

	public static void main(String[] args) {
		MyStack ms = new MyStack();
		
		System.out.println(ms.isEmpty());
		ms.push("a");
		System.out.println(ms.isEmpty());
		ms.push("b");									//a,b
		System.out.println(ms.size());
		ms.pop();
		ms.pop();										//null
		System.out.println(ms.isEmpty());
		ms.push("zzz");									//zzz
		System.out.println(ms.peek());
		ms.push("yyy");									//zzz,yyy
		System.out.println(ms.peek());
	}

}
