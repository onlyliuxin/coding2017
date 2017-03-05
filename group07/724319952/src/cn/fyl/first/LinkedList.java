package cn.fyl.first;

public class LinkedList implements List {

	private Node head,tail;	//头尾结点
	private int size;				//保存链表大小

	//将值插入链表
	public void add(Object o) {
		add(size(),o);
	}

	//将值从index位置插入链表
	public void add(int index, Object o) {
		if(index == 0){
			addFirst(o);
		}
		else if(index >= size){
			addLast(o);
		}
		else{
			Node current = head;
			for (int i = 1; i < index; i++) {
				current = current.next;
			}
			Node temp = current.next;
			current.next = new Node(o);
			(current.next).next = temp;
			size++;
		}
	}

	//取出index位置的值
	public Object get(int index) {
		if(index < 0 || index >=size){
			return null;
		}
		else if(index == 0){
			return head.data;
		}
		else if(index == size-1){
			return tail.data;
		}
		else{
			Node current = head;
			for (int i = 1; i < index; i++) {
				current = current.next;
			}
			Node temp = current.next;
			return temp.data;
		}
	}

	//删除index位置的值
	public Object remove(int index) {
		if(index < 0 || index >size)
		return null;
		else if(index ==0)
			return head.data;
		else if(index == size -1)
			return tail.data;
		else{
			Node previous = head;
			for (int i = 1; i < index; i++) {
				previous = previous.next;
			}
			Node current = previous.next;
			previous.next = current.next;
			size--;
			return current.data;
		}
	}

	public int size() {
		return size;
	}

	//添加头结点
	public void addFirst(Object o) {
		Node newNode = new Node(o);
		newNode.next = head;		//指向头引用所指结点
		head = newNode;			//头引用指向新增结点
		size++;
		if(tail == null){
			tail = head;
		}
	}

	//添加尾结点
	public void addLast(Object o) {
		Node newNode = new Node(o);
		if(tail == null){
			head = tail = newNode;
		}
		else{
		tail.next = newNode;
		tail = tail.next;
		}
		size++;
	}

	//删除头结点
	public Object removeFirst(){
		if(size == 0){
			return null;
		}
		else if(size == 1){
			Node temp = head;
			head = tail = null;
			size = 0;
			return temp.data;
		}
		else{
			Node temp = head;
			head = head.next;
			size--;
			return temp.data;
		}
	}

	//删除尾结点
	public Object removeLast() {
		if(size == 0){
			return null;
		}
		else if(size ==1){
			Node temp = head;
			head =tail =null;
			size =0;
			return temp.data;
		}
		else{
			Node current = head;
			for (int i = 0; i < size - 2; i++) {
				current = current.next;
			}
			Node temp =tail;
			tail = current;
			tail.next = null;
			size--;
			return temp.data;
		}
	}



	private static class Node {
		Object data;
		Node next;
		public Node(Object o){
			data = o;
		}
	}
	
	public static void main(String[] arg){
		LinkedList l = new LinkedList();
		l.add(0);
		l.add(2);
		l.add(4);
		l.add(3, 1);
		System.out.println(l.removeLast()+"  "+l.removeFirst()+"  "+l.get(0)+"  "+l.get(1));
	}
}
