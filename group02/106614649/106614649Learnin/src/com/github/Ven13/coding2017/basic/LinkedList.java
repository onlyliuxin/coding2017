package com.github.Ven13.coding2017.basic;

public class LinkedList implements List {
		
	//表示该链表的长度  
	private int size;
	
    //链表的头元素  
    private Node head;  
    //链表的尾元素  
    private Node tail;  
	
  //使用内部类来实现链表的每一个节点，每个节点有一个指向下一个元素的next，以及自身的data
  	private static class Node {
  		public Object data;
  		public Node next;
  				
  		public Node(Object data) {
  			this.data = data;
  		}
  	}	
    
    //链表的构造方法
    public LinkedList() {
    }
       
	@Override
	public void add(Object o) {
		add(size, o);
	}

	@Override
	public void add(int index, Object o) {
		if(index == 0) {
			addFirst(o);
		} else {
			if(index >= size) {
				addLast(o);
			} else {
				Node node = head;
				for (int i = 1; i < index; i++) {  
					head = head.next;  
				} 
				Node nextNode = node.next;  
				Node temp = new Node(o);  
				node.next = temp;  
				temp.next = nextNode;  
				size++;  
			}
		}
	}
		
	//添加前面
	public void addFirst(Object o) {
		Node newNode = new Node(o);
		newNode.next = head;
		head = newNode;
		size++;
		if(tail == null) {
			tail = head;
		}
	}
	
	//添加后面
	public void addLast(Object o) {
		if(tail == null) {
			tail = head = new Node(o);
		} else {
			Node newNode = new Node(o);
			tail.next = newNode;
			tail = tail.next;
		}
		size++;
	}
    
    
	@Override
	public Object get(int index) {
		Node node = head;
		for(int i = 0; i < index; i++) {
			node = node.next;
		}
		return node.data;
	}

	@Override
	public Object remove(int index) {
		if(size == 0) {
			throw new java.util.NoSuchElementException();  
		}
		if(index == 0) {
			Node node = head;
			Node temp = node.next;
			head = temp;
			size--;
			return node.data;
		} else {
			if(index >= size) {
				throw new java.util.NoSuchElementException();  
			} else {
				Node node = head;
				for(int i = 1; i < index; i++) {
					node = node.next;
				}
				Node temp = node.next;
				node.next = temp.next;
				size--;
				return node.data;
			}
		}
		
	}
	
	@Override
	public int size() {
		return size;
	}	
	
	public Object removeFirst() {
		//通过头指针创建头节点
		Node hNode = head;
		if (hNode == null) {
			throw new java.util.NoSuchElementException();  
		}
		Node nNode  = hNode.next;
		Object element = hNode.data;
		
		//移除
		hNode.data = null;
		hNode.next = null;
		head = nNode;
		//判断是否为尾节点
		if (nNode == null) {
			tail = null;
		}else {
			nNode = null;
		}
		size --;
		return element;
	}
	
	public Object removeLast() {
		return remove(size - 1);
	}
	
	public Iterator iterator() {
		return new LinkedListIterator();
	}
	
	private class LinkedListIterator implements Iterator {
		
		private Node node = head.next;  
		
		@Override
		public boolean hasNext() {
			return node != tail;  
		}

		@Override
		public Object next() {
			
            if(!hasNext()) {  
                throw new java.util.NoSuchElementException();  
            }  
            Object nextData = node.data;  
            node = node.next;   
            return nextData;  
		}
		
	}
	
	
}
