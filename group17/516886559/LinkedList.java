package com.rd.p2p.common.util.liuxin;

public class LinkedList<E> implements List<E> {
	
	private Node head;
	
	private int size;
	
	public void add(E o){
		if(size == 0){
			head = new Node();
			head.data = o;
		}else{
			Node node = new Node();
			node.data = o;
			getNode(size-1).next = node;
		}
		size ++;
	}
	
	public void add(int index , E o){
		Node node = new Node();
		node.data = o;
		if(index == 0){
			node.next = head;
			head = node;
		}else{
			Node indexNode = getNode(index - 1);
			indexNode.next = node;
			if(index < size){
				node.next = getNode(index);
			}
		}
		size ++;
	}
	
	public Object get(int index){
		return getNode(index).data;
	}
	
	public Object remove(int index){
		if(index > size - 1){
			throw new ArrayIndexOutOfBoundsException("移除索引超出数组索引边界   " + index + ">" + (size - 1));
		}
		if(index < 0){
			throw new ArrayIndexOutOfBoundsException("索引不能为负数");
		}
		Node returnNode = null;
		if(index == 0){
			returnNode = head;
			if(head.next != null){
				head = head.next;
			}else{
				head = null;
			}
		}else{
			returnNode = getNode(index);
			if(returnNode.next != null){
				Node preNode = getNode(index-1);
				Node nextNode = getNode(index+1);
				preNode.next = nextNode;
			}
		}
		size--;
		return returnNode.data;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(E o){
		add(0,o);
	}
	
	public void addLast(E o){
		add(size-1,o);
	}
	
	public Object removeFirst(){
		return remove(0);
	}
	
	public Object removeLast(){
		return remove(size - 1);
	}
	
	private Node getNode(int index){
		if(index > size - 1){
			throw new ArrayIndexOutOfBoundsException("查询索引超出数组索引边界   " + index + ">" + (size - 1));
		}
		if(index < 0){
			throw new ArrayIndexOutOfBoundsException("索引不能为负数");
		}
		Node tempNode = head;
		if(index == 0){
			tempNode =  head;
		}else{
			for(int i = 0; i < index; i++){
				tempNode = tempNode.next;
			}
		}
		return tempNode;
	}
	
	private static class Node{
		Object data;
		Node next;
	}

	@Override
	public String toString() {
		for (int i = 0; i < size; i++) {
			System.out.println(get(i));
		}
		return null;
	}
	
	//迭代器
	public Iterator iterator(){
		return new Iterator() {
			private int index = 0;
			
			@Override
			public Object next() {
				if(index >= size){
					throw new ArrayIndexOutOfBoundsException("取出数组索引不能大于等于数组总长度   " + index + ">=" + size);
				}
				return get(index++);
			}
			
			@Override
			public boolean hasNext() {
				if(size > index){
					return true;
				}else{
					return false;
				}
			}
		};
	}
	
	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.toString();
	}
}
