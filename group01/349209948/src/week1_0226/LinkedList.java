package week1_0226;

import java.util.NoSuchElementException;

public class LinkedList implements List {
	
	private Node head;
	private int size = 0;
	
	public void add(Object o){
		if (head == null) {
			head = new Node(o);
		} else {
			//遍历到链表的尾部
			Node tail = head;
			while (tail.next != null) {
				tail = tail.next;
			}
			Node node = new Node(o);
			tail.next = node;
		}
		size ++;
	}
	public void add(int index , Object o){
		rangeCheckForAdd(index);
		if (index ==0) {
			Node node = new Node(o);
			node.next = head;
			head = node;
		} else {
			Node preHead = head;
			for (int i = 0; i < index -1; i ++){
				preHead = head.next;
			}
			Node node = new Node(o);
			node.next = preHead.next;
			preHead.next = node;
		}
	}
	public Object get(int index){
		rangeCheck(index);
		Node dest = head;
		for (int i = 0; i< index; i++){
			dest = dest.next;
		}
		return dest.data;
	}
	private void rangeCheck(int index){
		if (index >= size || index <0){
			throw new IndexOutOfBoundsException(); 
		}
	}
	public Object remove(int index){
		rangeCheck(index);
		Node preDest = head;
		for (int i = 0; i < index; i++){
			preDest = preDest.next;
		}
		Node dest = preDest.next;
		preDest.next = dest.next;
		size --;
		return dest;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		Node node = new Node(o);
		node.next = head;
		head = node;
		size ++;
	}
	public void addLast(Object o){
		Node lastNode = head;
		while (lastNode.next != null){
			lastNode = lastNode.next;
		}
		Node node = new Node(o);
		lastNode.next = node;
		size++;
	}
	public Object removeFirst(){
		if (head == null) {
			throw new NoSuchElementException();
		}
		Node headTmp = head;
		head = head.next;
		size --;
		return headTmp;
	}
	public Object removeLast(){
		if (head == null) {
			throw new NoSuchElementException();
		}
		if (head.next == null) {
			Node dest = head;
			head = null;
			size --;
			return dest;
		}
		Node preLastNode = head;
		while(preLastNode.next.next != null) {
			preLastNode = preLastNode.next;
		}
		Node dest = preLastNode.next;
		preLastNode.next = null;
		size --;
		return dest;
	}
	public Iterator iterator(){
		return null;
	}
	private void rangeCheckForAdd(int index){
		if (index > size || index <0){
			throw new IndexOutOfBoundsException();
		}
	}
	
	private static  class Node{
		Object data;
		Node next;
		Node (Object data) {
			this.data = data;
			next = null;
		}
	}
}
