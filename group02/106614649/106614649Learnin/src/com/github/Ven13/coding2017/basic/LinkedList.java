package com.github.Ven13.coding2017.basic;

public class LinkedList implements List {
		
	//��ʾ������ĳ���  
	private int size;
	
    //�����ͷԪ��  
    private Node head;  
    //�����βԪ��  
    private Node tail;  
	
  //ʹ���ڲ�����ʵ�������ÿһ���ڵ㣬ÿ���ڵ���һ��ָ����һ��Ԫ�ص�next���Լ������data
  	private static class Node {
  		public Object data;
  		public Node next;
  				
  		public Node(Object data) {
  			this.data = data;
  		}
  	}	
    
    //����Ĺ��췽��
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
		
	//���ǰ��
	public void addFirst(Object o) {
		Node newNode = new Node(o);
		newNode.next = head;
		head = newNode;
		size++;
		if(tail == null) {
			tail = head;
		}
	}
	
	//��Ӻ���
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
		//ͨ��ͷָ�봴��ͷ�ڵ�
		Node hNode = head;
		if (hNode == null) {
			throw new java.util.NoSuchElementException();  
		}
		Node nNode  = hNode.next;
		Object element = hNode.data;
		
		//�Ƴ�
		hNode.data = null;
		hNode.next = null;
		head = nNode;
		//�ж��Ƿ�Ϊβ�ڵ�
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
