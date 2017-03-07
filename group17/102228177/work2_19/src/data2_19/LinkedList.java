package data2_19;

import java.util.NoSuchElementException;

public class LinkedList implements List {
	
	private Node head;
	private int size;
	
	public LinkedList(){
		size = 0;
		head = null;
	}
	
	public void add(Object o){
		Node node = new Node(o);
		if(head == null){
			head = node;
		}else{
			//p为游标   从头遍历到尾
			Node p = head;
			while(p.next != null){
				p = p.next;
			}
			p.next = node;
		}
		size++;
	}
	
	public void add(int index , Object o){
		//判断不为空链表
		if(head != null){
			Node p = head;
			int k = 0;
			//扫描单链表查找第index-1个节点
			while(k < index-1 && p.next != null){
				k++;
				p = p.next;
			}
			//判断是否找到第index-1个节点
			if(p != null){
				Node node = new Node(o);
				node.next = p.next;
				p.next = node;
			}
			size++;
		}
	}
	
	public Object get(int index){
		if(index <0 || index >= size){
			throw new IndexOutOfBoundsException();
		}else{
			Node p = head;
			int k = 0;
			while(k < index && p.next != null){
				k++;
				p = p.next;
			}
			return p.data;
		}
	}
	public Object remove(int index){
		if(index <0 || index >= size){
			throw new IndexOutOfBoundsException();
		}else{
			if(head != null){
				Node p = head;
				int k = 0;
				while(k > index-1 && p.next != null){
					k++;
					p = p.next; 
				}
				Node next = p.next;
				p.next = next.next;
				size--;
				return next.data;
			}
		}
		return null;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		Node node = new Node(o);
		node.next = head;
		head = node;
		size++;
	}
	
	public void addLast(Object o){
		Node node = new Node(o);
		if(head == null){
			head = node;
		}else{
			Node p = head;
			while(p.next != null){
				p = p.next; 
			}
			p.next = node;
		}
		size++;
	}
	
	public Object removeFirst(){
		if(head == null){
			 throw new NoSuchElementException();
		}
		Node node = head;
		head = node.next;
		size--;
		return node.data;
	}
	public Object removeLast(){
		if(head == null){
			 throw new NoSuchElementException();
		}else{
			Node p = head;
			int k = 0;
			while(k < size-1 && p.next != null){
				k++;
				p = p.next;
			}
			Node last = p.next;
			p.next = null;
			size--;
			return last.data;
		}
	}
	
	private static class Node{
		Object data;
		Node next;
		private Node(Object o){
			this.data = o;
			this.next = null;
		}
	}
}