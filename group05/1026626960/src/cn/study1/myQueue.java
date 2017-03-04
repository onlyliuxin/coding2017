package cn.study1;

public class myQueue<T> {
	private class Node{
		T t;
		Node next;
	}
	private Node first;
	private Node last;
	private int N;
	public boolean isEmpty(){
		return N==0;
	}
	public int size(){
		return N;
	}
	public void enqueue(T t){
		Node oldlast = last;
		last = new Node();
		last.t = t;
		last.next = null;
		if(isEmpty()){
			first = last;
		}else{
			oldlast.next = last;
		}
		N++;
	}
	public T dequeue(){
		T t = first.t;
		first = first.next;
		if(isEmpty()){
			last = null;
		}
		N--;
		return t;
	}
}
