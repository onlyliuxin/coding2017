package DataStructure_3;

import java.util.Stack;

import DataStructure_1.LinkedList.Node;

public class LinkedList {
	private Node head;
	static int size = 0;
	public void add(Object o){
		if(null == head){
			head = new Node();
			head.data = o;
			head.next = null;
		}else{
			Node p = head;
			while(null != p.next){
				p = p.next;
			}
			Node newNode = new Node();
			newNode.data = o;
			p.next = newNode;
			newNode.next =null;
		}
		size++;
	}
	public int size(){
		return size;
	}
	public void add(int index,Object o){
		if(index < 0){
			throw new RuntimeException("下标不能为负数");
		}
		if(index == 0){
			addFirst(o);
			size++;
			return;
		}
		if(index > size){
			throw new RuntimeException("");
		}
		int i = 0;
		Node p = head;
		Node q = null;

		while(i!=index){
			q = p;
			p = p.next;
			i++;
		}
		Node r = new Node();
		r.data = o;
		r.next =null;
		q.next = r;
		r.next = p;
		size++;
		return;
	}
	
	public Object get(int index){
		int i = 0;
		Node p = head;
		while(i != index){
			p = p.next;
			i++;
		}
		return p.data;
	}
	public Object remove(int index){
		if(index < 0){
			throw new RuntimeException("下标不能为负数");
		}
		if(index == 1){
			size--;
			return head.data;
		}
		int i = 0;
		Node p = head;
		Node q = null;
		while(i != index){
			q = p;
			p = p.next;
			i++;
		}
		q.next = p.next;
		size--;
		return p.data;
	}
	public void addFirst(Object o){
		Node p = new Node();
		p.next = head;
		p.data = o;
		head = p;
		size++;
	}
	public Object removeFirst(){
		head = head.next;
		size--;
		return null;
	}
	public static class Node{
		Object data;
		Node next;
	}
	
	/**
	 *  把该链表逆置
	 *  例如链表 3->7->10  ，逆置后变为  10->7->3
	 */
	public void reverse(){
		if(null == head || null == head.next){
			return;
		}
		Stack<Node> s = new Stack<Node>();
		Node curNode = head;
		while(curNode != null){
			s.push(curNode);
			Node nextNode = curNode.next;
			curNode.next = null; // 把链断开，回收
			curNode = nextNode;
		}
		
		head = s.pop();
		curNode = head;
		while(!s.isEmpty()){
			Node nextNode = s.pop();
			curNode.next = nextNode;
			curNode = nextNode;
		}
		
	}
	
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("[");
		Node node = head;
		while(node != null){
			buffer.append(node.data);
			if(node.next != null){
				buffer.append(",");
			}
			node = node.next;
		}
		buffer.append("]");
		return buffer.toString();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
