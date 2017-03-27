package com.coding.basic;

import java.util.NoSuchElementException;

public class LinkedList implements List {
	
	
	private Node first;
	
	private Node last;
	
	private int size = 0;
	
	public void add(Object o){
		if(null == first){
			//当链表元素为空时，新建一个Node
			Node node = new Node();
			node.data = o;
			node.next = null;
			first = node;
			last = node;
			size ++;
		}else{
			addLast(o);
		}
	}
	public void add(int index , Object o){
		if(index < 0 || index >= size){
			//数组越界异常
			throw new IndexOutOfBoundsException();
		}else{
			if(0 == index){
				//1.如果加在头上
				addFirst(o);
			}else{
				//2.加在中间位置
				Node node = first.next;
				int nodeIndex = 1;
				if(nodeIndex == index){
					//如果是第二个位置的话
					Node nodeAdd = new Node();
					nodeAdd.data = o;
					first.next = nodeAdd;
					nodeAdd.next = node;
					last = node;
					size ++;
				}
				//第三个位置及以后、开始遍历所有的索引
				while(null != node.next){
					//保留遍历中node之前的结点
					Node nodeLast = node;
					node = node.next;
					nodeIndex++;
					if(nodeIndex == index){
						Node nodeAdd = new Node();
						nodeAdd.data = o;
						nodeLast.next = nodeAdd;
						nodeAdd.next = node;
						size ++;
						break;
					}
				}
			}
		}
		
	}
	public Object get(int index){
		if(index < 0 || index >= size){
			//数组越界异常
			throw new IndexOutOfBoundsException();
		}else{
			if(0 == index){
				//1.如果加在头上
				return first.data;
			}
			Node node = first.next;
			int nodeIndex = 1;
			if(nodeIndex == index){
				//如果是第二个位置的话
				return node.data;
			}
			//第三个位置及以后、开始遍历所有的索引
			while(null != node.next){
				//保留遍历中node之前的结点
				node = node.next;
				nodeIndex++;
				if(nodeIndex == index){
					return node.data;
				}
			}
		}
		throw new IndexOutOfBoundsException();
	}
	public Object remove(int index){
		if(index < 0 || index >= size){
			//数组越界异常
			throw new IndexOutOfBoundsException();
		}else{
			if(0 == index){
				//1.如果移除头
				removeFirst();
			}else if(index == (size - 1)){
				//2.移除尾
				removeLast();
			}else{
				//3.移除中间位置
				Node node = first.next;
				//从first的零号索引开始
				int nodeIndex = 1;
				
				//开始遍历所有的索引，记住要移除的索引位数据的前后结点
				Node lastNode = first;
				if(index == nodeIndex){
					//第一次不匹配则后续的循环执行
					Object o = node.data;
					lastNode.next = node.next;
					size--;
					return o;
				}else{
					while(null != node.next){
						lastNode = node;
						node = node.next;
						nodeIndex++;
						if(index == nodeIndex){
							Object o = node.data;
							lastNode.next = node.next;
							size--;
							return o;
						}
					}
				}
			}
		}
		throw new IndexOutOfBoundsException();
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		Node node = new Node();
		node.data = o ;
		node.next = first;
		first = node;
		size++;
	}
	public void addLast(Object o){
		Node node = new Node();
		node.data = o ;
		node.next = null;
		last.next = node;
		last = node;
		size++;
	}
	public Object removeFirst(){
		Object o = first.data;
		Node node = first.next;
		first = node;
		size--;
		return o;
	}
	public Object removeLast(){
		if(0 == size){
			throw new NoSuchElementException();
			
		}else if(1 == size){
			//只有一个元素
			removeFirst();
		}else{
			//第二个元素
			Node node = first.next;
			if(null == node.next){
				Object o = node.data;
				last = first;
				first.next = null;
				return o;
			}else{
				while(null != node.next){
					//若不止只有2个 ，记录最后一个结点的前一个。
					Node lastNode = node;
					node = node.next;
					if(null == node.next){
						Object o = node.data;
						lastNode.next = null;
						last = lastNode;
						size--;
						return o;
					}
				}
			}
		}
		throw new NoSuchElementException();
	}
	public Iterator iterator(){
		return null;
	}
	
	
	private static  class Node{
		Object data;
		Node next;
		
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(null != first){
			sb.append(first.data.toString() + ",");
			Node node = first.next;
			sb.append(node.data.toString() + ",");
			while(null != node.next){
				node = node.next;
				sb.append(node.data.toString() + ",");
			}
		}
		return sb.toString();
	}


	
}
