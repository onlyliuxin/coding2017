package com.github.ZhoufeifeiJAVA.coding2017.basic;
public class StackTest{
	public static void sop(Object o){
		System.out.println(o);
	}
	public static void main(String[] args){
		Stack s = new Stack();
		s.push("String0");
		s.push("String1");
		s.push("String2");
		sop("the queue is not empty "+s.isEmpty());
		sop("the size of queue is "+s.size());
		sop("out queue "+s.pop());
		sop("just watch queue,not delete "+s.peek());
		sop("the size of queue is "+s.size());
	}
}