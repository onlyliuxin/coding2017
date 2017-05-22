package week09.basic;

import java.util.NoSuchElementException;

public class Queue<T> {
	private Node<T> first;
	private Node<T> last;
	private int size;
	
	private static class Node<T>{
		private Node<T> next;
		private T data;
	}
	
	public Queue(){
		first = null;
		last = null;
		size = 0;
	}
	
	public boolean isEmpty(){
		return first == null;
	}
	
	public int size(){
		return size;
	}
	
	public void enQueue(T data){
		Node<T> oldlast = last;
		last = new Node<T>();
		last.data = data;
		last.next = null;
		if(isEmpty()){
			first = last;
		}else{
			oldlast.next = last;
		}
		size ++;
	}
	
	public T deQueue(){
		if(isEmpty()){
			throw new NoSuchElementException("Queue is Empty");
		}
		T item = first.data;
		first = first.next;
		size --;
		if(isEmpty()){
			last = null;
		}
		return item;
	}
	
	@Override
	public String toString(){
		if(isEmpty()){
			return "[]";
		}
		
		StringBuffer sb = new StringBuffer("[");
		Node<T> node = first;
		while(node != null){
			if(node.next == null){
				sb.append(node.data);
			}else{
				sb.append(node.data);
				sb.append(",");
			}
			node = node.next;
		}
		sb.append("]");
		return sb.toString();
	}
}
