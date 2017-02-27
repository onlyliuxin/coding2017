import java.util.Objects;

public class LinkedList implements List {
	
	private Node head;
	
	public void add(Object o){
		if(head == null) {
			head = new Node();
			head.data = o;
		} else {
			Node temp = head;
			while(temp.next != null) {
				temp = temp.next;
			}
			temp.next = new Node();
			temp.next.data = o;
		}
	}

	public void add(int index , Object o){
		if(index > size()) {
			return ;
		}
		if(index == 0) {
			Node newNode = new Node();
			newNode.data = o;
			newNode.next = head;
			head = newNode;
		} else {
			int temp = 0;
			Node newNode = new Node();
			newNode.data = o;
			Node tempNode = head;
			while(temp != index - 1) {
				tempNode = tempNode.next;
				++temp;
			}
			Node tempNode2 = tempNode.next;
			tempNode.next = newNode;
			newNode.next = tempNode2;
		}
	}

	public Object get(int index){
		if(index > size() || size() == 0) {
			return null;
		}
		Node temp = head;
		for(int i = 0; i < index; ++i) {
			temp = temp.next;
		}

		return temp.data;
	}


	public Object remove(int index){
		if(size() == 0) {
			return null;
		}
		if(size() == 1) {
			Object obj = head.data;
			head = null;
			return obj;
		}
		if(index == 0) {
			Node temp = head;
			head = head.next;
			temp.next = null;
		} else if(index > size()) {
			return null;
		} else {
			int t = 0;
			Node temp = head;
			while(t != index - 1) {
				temp = temp.next;
				++t;
			}
			Node result = temp.next;
			temp.next = temp.next.next;
			return result.data;
		}
		return null;
	}
	
	public int size(){
		int len = 0;
		Node temp = head;
		while(temp != null) {
			temp = temp.next;
			++len;
		}
		return len;
	}
	
	public void addFirst(Object o){
		Node temp = new Node();
		temp.data = o;
		temp.next = head;
		head = temp;
	}
	public void addLast(Object o){
		Node newNode = new Node();
		newNode.data = o;

		if(size() == 0) {
			head = newNode;
		}
		Node temp = head;
		while(temp.next != null) {
			temp = temp.next;
		}
		temp.next = newNode;
	}
	public Object removeFirst(){
		if(size() == 0) {
			return null;
		}
		Node temp = head;
		head = head.next;
		return temp.data;
	}
	public Object removeLast(){
		if(size() == 0) {
			return null;
		}
		if(size() == 1) {
			Node temp = head.next;
			head = head.next;
			return temp.data;
		}
		Node temp1 = head;
		Node temp2 = head.next;
		while(temp2.next != null) {
			temp1 = temp1.next;
			temp2 = temp2.next;
		}
		temp1.next = null;
		return temp2.data;
	}
	public Iterator iterator(){
		return null;
	}
	
	
	private static  class Node{
		Object data;
		Node next;
		
	}
}
