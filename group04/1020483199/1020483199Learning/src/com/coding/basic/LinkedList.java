package com.coding.basic;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class LinkedList implements List {

	/**
	 * 当前节点的大小
	 */
	private int size;
	/**
	 * 头节点
	 */
	private Node header;
	/**
	 * 尾节点
	 */
	private Node end;
	
	

	public void add(Object o){
		if(header==null){
			header = new Node();
			header.data = o;
			end = header;
		}else{
			Node n = new Node();
			n.data = o;
			end.next = n;
			n.previous=end;
			end = n;
		}
		size++;
	}
	public void add(int index,Object o){
		judge(index);
		Node n = new Node();
		n.data = o;
		Node temp = header;
		for(int i = 0;i<index;i++){
			temp = temp.next;//此时temp是index位置的原始节点
		}
		if(temp.previous!=null&&temp.next!=null){//前后节点不为空的时候
			Node pe=temp.previous;
			pe.next=n;
			n.previous = pe;
			n.next=temp;
			temp.previous=n;
		}else if(temp.previous==null){
			header=n;
			n.next=temp;
			temp.previous=n;
		}else if(temp.next==null){
			n.previous=temp;
			temp.next=n;
			end=n;
		}
		size++;
	}
	public Object get(int index){
		judge(index);
		Node n = header;
		for(int i = 0;i < index; i++){
			n=n.next;
		}
		return n.data;
	}
	public Object remove(int index){
		judge(index);
		Node temp = header;
		for(int i=0;i<index;i++){
			temp = temp.next;
		}
		Object value = temp.data;
		if(temp.previous!=null&&temp.next!=null){//前后节点不为空的时候
			temp.next.previous=temp.previous;
			temp.previous.next=temp.next;
		}else if(temp.previous==null){
			header=temp.next;
			temp.next.previous = null;//消除当前对象的前链接
			temp = null;//消除当前对象
		}else if(temp.next==null){
			end=temp.previous;
			temp.previous.next = null;//消除当前对象的后连接
			temp = null;//消除当前对象
		}
		size--;
		return value;
	}

	public int size(){
		return size;
	}

	public void addFirst(Object o){
		Node n = new Node();
		n.data = o;
		header.previous = n;
		n.next = header;
		header=n;
		size++;
	}
	public void addLast(Object o){
		Node n = new Node();
		n.data = o;
		end.next = n;
		n.previous=end;
		end=n;
		size++;
	}
	public Object removeFirst(){
		Object value = header.data;
		header.next.previous = null;
		header=header.next;
		size--;
		return value;
	}
	public Object removeLast(){
		Object value = end.data;
		end.previous.next = null;
		end=end.previous;
		size--;
		return value;
	}
	public Iterator iterator(){
		
		return new LinkedListIterator();
	}
	
	private class LinkedListIterator implements Iterator{
		int cursor;
		int lastReset = -1;
		@Override
		public boolean hasNext() {
			return size!=cursor;
		}

		@Override
		public Object next() {
			//标记索引当前位置
			int i = cursor;
			if(i>size){
				throw new NoSuchElementException();
			}
			Node n = header;
			for(int j = 0;j < i;j ++){
				n = n.next;
			}
			cursor = i + 1;
			lastReset = i;
			return n.data;
		}
		
	}


	/**
	 * 判断数组是否越界方法
	 */
	public void judge(int index){
		if(index<0||index>=size){
			throw new IndexOutOfBoundsException("数组越界");
		}
	}
	/**
	 * @author sulei
	 */
	private static class Node{
		Object data;//当前节点数据
		Node next;//下一个节点
		Node previous;//上一个节点
	}
}
