package cn.study1;

public class myStack<T> {
	private class Node{
		T t;
		Node next;
	}
	private Node first;
	private int N;
	public boolean isEmpty(){
		return N==0;
	}
	public int size(){
		return N;
	}
	public void push(T t){
		Node oldfirst = first;
		first = new Node();
		first.t = t;
		first.next = oldfirst;
		N++;
	}
	public T pop(){
		T t = first.t;
		first = first.next;
		N--;
		return t;
	}
}
