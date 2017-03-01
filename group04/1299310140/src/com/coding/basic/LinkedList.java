package com.coding.basic;

public class LinkedList implements List {
	
	private Node head;
	private int size = 0;
	
	public void add(Object o){
		if(this.size == 0){//size为0，给head赋值
			this.head = new Node(o);
			size++;
		}else{//将o加至链表末尾
			Node Last = new Node(o);
			Node pres = this.head;
			while(pres.next != null){
				pres = pres.next;
			}
			pres.next = Last;
			size++;
		}
	}
	
	public void add(int index , Object o){//index:0~size
		if(index < 0 || index > this.size){//index小于0or大于size，参数错误
			return;
		}
		if(index == 0){//将o加至链表头部
			//size为0时，index必为0，执行该分支
			Node first = new Node(o);
			first.next = this.head;
			this.head = first;
			size++;
		}else if(index == size){//将o加至链表末尾
			//index == size != 0时，执行该分支
			Node Last = new Node(o);
			Node pres = this.head;
			while(pres.next != null){
				pres = pres.next;
			}
			pres.next = Last;
			size++;
		}else{
			//0<index<size时，执行该分支
			Node pres = this.head;//pres指向0
			for(int i = 0;i < index-1;i++){
				pres = pres.next;
			}
			
			//此时pres指向index-1
			Node insert = new Node(o);
			insert.next = pres.next;
			pres.next = insert;
			size++;
		}
	}
	
	public Object get(int index){//index:0~size-1
		if(index < 0 || index >= this.size){//index小于0or大于等于size，参数错误
			return null;
		}
		
		Node pres = this.head;//pres指向0
		for(int i = 0;i < index;i++){
			pres = pres.next;
		}
		//此时pres指向index
		return pres.data;
	}
	
	public Object remove(int index){//index:0~size-1
		if(index < 0 || index >= this.size){//index小于0or大于等于size，参数错误
			return null;
		}
		
		Object o = null;
		if(index == 0){//删除头节点
			o = this.head.data;
			this.head = this.head.next;
			size--;
		}else{//删除头节点以外的其他节点
			Node pres = this.head;//pres指向0
			for(int i = 0;i < index-1;i++){
				pres = pres.next;
			}
			
			//此时pres指向index-1
			o = pres.next.data;
			pres.next = pres.next.next;
			size--;
		}
		return o;
	}
	
	public int size(){
		return this.size;
	}
	
	public void addFirst(Object o){//同add(int 0 , Object o)
		Node first = new Node(o);
		first.next = this.head;
		this.head = first;
		size++;
	}
	
	public void addLast(Object o){//同add(int size , Object o)
		if(this.size == 0){
			this.head = new Node(o);
			size++;
		}else{//size>=1
			Node Last = new Node(o);
			Node pres = this.head;
			while(pres.next != null){
				pres = pres.next;
			}
			pres.next = Last;
			size++;
		}
	}
	
	public Object removeFirst(){//同remove(int 0)
		if(this.size == 0){
			return null;
		}
		
		Object o = this.head.data;
		this.head = this.head.next;
		size--;
		return o;
	}
	
	public Object removeLast(){
		if(this.size == 0){
			return null;
		}
		
		Object o = null;
		if(this.size == 1){//size==1
			o = this.head.data;
			this.head = null;
			this.size--;
		}else{//size>=2
			Node pres = this.head;
			while(pres.next.next != null){
				pres = pres.next;
			}
			o = pres.next.data;
			pres.next = null;
			size--;
		}
		return o;
	}
	
	public Iterator iterator(){
		return new LinkedListIterator(this);
	}
	
	private static class LinkedListIterator implements Iterator{
//		private LinkedList list;
		private Node pres;
		
		public LinkedListIterator(LinkedList list) {
			super();
//			this.list = list;
			this.pres = list.head;
		}

		@Override
		public boolean hasNext() {
			if(this.pres != null){
				return true;
			}else{
				return false;
			}
		}

		@Override
		public Object next() {
			Object o = this.pres.data;
			pres = pres.next;
			return o;
		}
		
	} 
	
	private static class Node{
		Object data;
		Node next;
		
		public Node(Object data) {
			super();
			this.data = data;
		}
	}
	
	public String toString(){
		String result = "[";
		if(this.size == 0){
			result = result + "]";
			return result;
		}else{
			Node pres = this.head;
			while(pres != null){
				result = result + pres.data + ",";
				pres = pres.next;
			}
			result = result.substring(0,result.length()-1);
			result = result + "]";
			return result;
		}
	}
}
