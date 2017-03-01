package com.my.list;

/**
 * 实现 LinkedList
 *
 */
public class LinkedList {
	
	//首节点
	Node first = new Node();
	//集合大小
	int size = 0;
	
	/**
	 * 添加元素
	 * @param object
	 * @return
	 */
	public Object add(Object object){
		if (size == 0) {
			first.setObject(object);
		}else{
			Node previous = first;
			Node temp = first.getNext();
			while (temp != null) {
				previous = temp;
				temp = temp.getNext();
			}
			Node node = new Node();
			node.setObject(object);
			previous.setNext(node);
		}
		++size;
		return object;
	}
	
	/**
	 * 根据下标添加元素
	 * @param index
	 * @param object
	 * @return
	 */
	public Object add(int index , Object object){
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		
		if (size == 0) {
			first.setObject(object);
		}else{
			if (index == 0) {
				Node temp = new Node();
				temp.setObject(object);
				temp.setNext(first);
				first = temp;
			}else{
				int count = 1;
				Node temp = first;
				while (temp != null) {
					if (count++ == index) {
						Node next = temp.getNext();
						Node node = new Node();
						temp.setNext(node); 
						node.setObject(object);
						node.setNext(next);
						break;
					}
					temp = temp.getNext();
				}
			}
		}
		++size;
		return object;
	}
	
	/**
	 * 根据下标删除元素
	 * @param index
	 * @return
	 */
	public Object remove(int index){
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		Node node = null;
		if (index == 0) {
			Node next = first.getNext();
			first = next;
			node = first;
		}else{
			int count = 1;
			Node temp = first;
			while (temp != null) {
				if (count++ == index) {
					node = temp.getNext();
					Node next = node.getNext();
					temp.setNext(next); 
					break;
				}
				temp = temp.getNext();
			}
		}
		--size;
		return node.getObject();
	}
	
	/**
	 * 根据下标获取元素
	 * @param index
	 * @return
	 */
	public Object get(int index) {
		Node temp = first;
		int count = 0;
		while (temp != null) {
			if (count++ == index) {
				break;
			}
			temp = temp.getNext();
		}
		return temp.getObject();
	}
	
	/**
	 * 获取集合大小
	 * @return
	 */
	public int size() {
		return size;
	}
	
	
}


/**
 * 节点元素
 *
 */
class Node{
	private Object object ;
	private Node next;
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	public Node getNext() {
		return next;
	}
	public void setNext(Node next) {
		this.next = next;
	}
	
}
