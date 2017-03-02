package com.dong.week1;

public class LinkedList implements List {
	
	private int size = 0;
	private Node head;
	private Node tail;
	
	
	public void add(Object o){
	//���һ��Ԫ�ص��߼��ǳ��򵥣�ֻ��Ҫ�ж�head�Ƿ�Ϊ�ա���Ϊ�գ�����ֱ�ӼӼ���
		Node node = new Node(o,null);		
		if(head ==null){
			head =node;
		}else{
			tail.next=node;			
		}
		tail=node;
		size++;
		
	}
	public void add(int index , Object o){
		if(index < 0){
			throw new ArrayIndexOutOfBoundsException("index����Ϊ����");
		}
		if(index > size){
			throw new ArrayIndexOutOfBoundsException("��ǰlist����Ϊ"+size+",��ȡ�����ǣ�"+index);
		}
		if(size==0){
			head=new Node(o, null);		
			tail=head;
			return;
		}
		if(index==0){
			Node curNode =head;
			Node newNode =new Node(o, curNode);
			head=newNode;
			return;
		}
		Node curNode =head;
		Object retVal = null;
		for(int i=0;i<index-1;i++){
			curNode=curNode.next;
		}
		if(curNode.next==null){
			curNode.next = new Node(o, null);
			tail = curNode.next;
			return;
		}	
		
		Node newNode = new Node(o, curNode.next);
		curNode.next = newNode;
		size ++;
	}
	public Object get(int index){
		if(size ==0){
			throw new ArrayIndexOutOfBoundsException("��ǰlistΪ�գ��޷��Ƴ�");
		}
		if(index >= size){
			throw new ArrayIndexOutOfBoundsException("��ǰlist����Ϊ"+size+",�Ƴ������ǣ�"+index);
		}
		Node curNode = head;
		for(int i=0;i<index;i++){
			curNode = curNode.next;
		}
		return curNode.data;
	}
	public Object remove(int index){
		if(size ==0){
			throw new ArrayIndexOutOfBoundsException("��ǰlistΪ�գ��޷���ȡ");
		}
		if(index >= size){
			throw new ArrayIndexOutOfBoundsException("��ǰlist����Ϊ"+size+",��ȡ�����ǣ�"+index);
		}
		Object retVal = null;
		if(index==0){
			retVal =head.data;
			head=head.next;
			return retVal;
		}
		Node curNode =head;
		
		for(int i=0;i<index-1;i++){
			curNode=curNode.next;
		}
		retVal = curNode.next.data;
		if(curNode.next==tail){
			tail=curNode;
		}		
		curNode.next = curNode.next.next;
		size --;
		return retVal;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		Node node = new Node(o,head);		
		head=node;
		size++;
		
	}
	public void addLast(Object o){
		tail.next = new Node(o, null);
		tail=tail.next;
		size++;
		
	}
	public Object removeFirst(){
		if(size ==0){
			throw new ArrayIndexOutOfBoundsException("��ǰlistΪ�գ��޷��Ƴ�");
		}
		Object retVal = head.data;
		head.data=null;
		head=head.next;		
		size--;
		return retVal;
	}
	public Object removeLast(){
		Object retVal;
		if(size ==0){
			throw new ArrayIndexOutOfBoundsException("��ǰlistΪ�գ��޷��Ƴ�");
		}else if(size==1){
			retVal= head.data;
			head=null;
			tail=null;
			size--;
			return retVal;
		}
		Node curNode = head;
		while(curNode.next.next!=null){
			curNode= curNode.next;
		}
		tail =curNode;
		retVal=curNode.next.data;
		curNode.next=null;
		size--;
		return retVal;
	}
	public Iterator iterator(){
		return new IteratorArrayLinkedList(this);
	}
	
	
	private static  class Node{
		Object data;
		Node next;
		public Node(Object data, Node next) {
			super();
			this.data = data;
			this.next = next;
		}
		@Override
		public String toString() {
			return "Node [data=" + data + ", next=" + next + "]";
		}	
		
		
	}
	
	
	@Override
	public String toString() {
		return "LinkedList [size=" + size + ", head=" + head + ", tail=" + tail + "]";
	}
	
	
	private class IteratorArrayLinkedList implements Iterator{
		private LinkedList list;
		private int index = 0;
		
			


		public IteratorArrayLinkedList(LinkedList list) {
			super();
			this.list = list;
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return this.list.size() >index;
		}

		@Override
		public Object next() {
			// TODO Auto-generated method stub
			if(hasNext()){
				return  this.list.get(index++);
			}
			return null;
		}
}
	public static void main(String[] args) {
		
		LinkedList arrayList= new LinkedList();
		
		Iterator iterator=  arrayList.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
	
}
	
}
