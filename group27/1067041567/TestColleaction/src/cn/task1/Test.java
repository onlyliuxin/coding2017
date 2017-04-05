package cn.task1;

import java.util.Queue;
import java.util.Stack;

public class Test {
	public static void main(String[] args) {
		
		Stack<Object> obj = new Stack<>();
		//Queue<Object> queue = new 
		
		obj.push("a");
		obj.push("b");
		obj.push("c");
		obj.push("d");
		obj.peek();
		System.out.println(obj.peek());
		System.out.println(obj.size());
	}
}
