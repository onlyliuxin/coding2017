package com.coding.basic;


public class LinkedList implements List{

	//private Node head;
	private Node pre; //指向当前结点的前一个元素
	private Node pHead; //头节点指向第一个元素
	private Node cur; //指向链表的最后一个元素
	private int num = 0; //链表中的元素个数
	
	public void add(Object o){
		Node node = new Node();
		node.data = o;
		if(pHead == null){ //链表为空，从第一个元素添加
			pHead = cur = node;
			pHead.pre = null;
		}else{
			node.pre = cur; //前一结点向后移动一位
			cur.next = node; // 添加元素
			cur = cur.next; //当前结点向后移动一位
		}
		num++; //链表数目增1
	}
	
	/**
	 * 根据索引找到对应的结点
	 * @param index
	 * @return
	 */
	public Node findNode(int index){
		Node node = pHead;
		int tem = 0;
		while(tem++ != index){
			node = node.next;
		}
		return node;
	}
	
	public void add(int index , Object o){
		if(num == 0 || index == num){
			add(o);
			return;
		}
		if(index <= num-1 && index > 0){
			Node node = new Node();
			node.data = o;
			Node tem = findNode(index);
			Node preNode = tem.pre;
			Node posNode = tem.next;
			preNode.next = node;
			node.next = posNode;
			posNode.pre = node;
			num++;
			return;
		}
		if(index == 0){
			Node node = new Node();
			node.data = o;
			pHead.pre = node;
			node.next = pHead;
			pHead = node;
			num++;
			return;
		}
		throw new IndexOutOfBoundsException();
	}
	public Object get(int index){
		if(index <= num - 1 && index >= 0){
			return findNode(index).data;
		}
		throw new IndexOutOfBoundsException();
	}
	
	public Object remove(int index){
		Object result;
		if(index >0 && index < num - 1){ //删除链表中间的元素
			Node node = findNode(index);
			result = node.data;
			Node preNode = node.pre;
			Node posNode = node.next;
			preNode.next = posNode;
			posNode.pre = preNode;
			num--;
			return result;
		}
		if(index == 0 && num > 0){ //删除第一个元素
			Node node = pHead.next;
			result = pHead.data;
			node.pre = null;
			pHead = node;
			num--;
			return result;
		}
		if(index == num - 1 && num > 0){ //删除最后一个元素
			result = cur.data;
			cur = cur.pre;
			cur.next = null;
			num--;
			return result;
		}
		throw new IndexOutOfBoundsException();
	}
	
	public int size(){
		return num;
	}
	
	public void addFirst(Object o){
		if(num == 0){
			add(o);
			return;
		}
		if(num > 0){
			Node node = new Node();
			node.data = o;
			node.pre = null;
			node.next = pHead;
			pHead = node;
			num++;
			return;
		}
		throw new IndexOutOfBoundsException();
		
	}
	public void addLast(Object o){
		if(num == 0){
			add(o);
			return;
		}
		if(num > 0){
			Node node = new Node();
			node.data = o;
			node.pre = cur;
			cur.next = node;
			node.next = null;
			cur = node;
			num++;
			return;
		}
		throw new IndexOutOfBoundsException();
	}
	public Object removeFirst(){
		Object result;
		if(num > 0){
			result = pHead.data;
			if(num == 1){
				pHead = null;
				num = 0;
			}
			if(num > 1){
				pHead = pHead.next;
				pHead.pre = null;
				num--;
			}
			return result;
		}
		throw new IndexOutOfBoundsException();
	}

	public Object removeLast(){
		Object result;
		if(num == 1){
			result = pHead.data;
			pHead = null;
			num = 0;
			return result;
		}
		if(num > 1){
			
			result = cur.data;
			cur = cur.pre;
			cur.next = null;
			num--;
			return result;
		}
		throw new IndexOutOfBoundsException();
	}
	public Iterator iterator(){
		return new Iterator(){
			int cur = 0;
			Node node = pHead;
			@Override
			public boolean hasNext() {
				if(cur++ < num){
					return true;
				}
				return false;
			}

			@Override
			public Object next() {
				Object result = node.data;
				node = node.next;
				return result;
			}
			
		};
	}
	
	
	private static  class Node{
		Object data;
		Node pre;
		Node next;
		
	}

	public static void main(String[]args){
		LinkedList list = new LinkedList();
		list.add(1);
//		list.add(2);
//		list.add(3);
//		list.add(4);
//		list.add(0, 0);
//		list.addFirst(0);
//		list.addLast(5);
//		list.removeFirst();
		System.out.println(list.removeLast());
		Iterator it = list.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
	
}















