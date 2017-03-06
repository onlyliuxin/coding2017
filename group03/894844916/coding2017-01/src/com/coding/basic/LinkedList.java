/**
 * 
 */
package com.coding.basic;

/**
 * @author patchouli
 *
 */
public class LinkedList implements List, Iterator {

	private Node head;
	private Node current=head;
	private int size=0;
	
	/* (non-Javadoc)
	 * @see nusub.coding2017.basic.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
		if (current.next==null) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see nusub.coding2017.basic.Iterator#next()
	 */
	@Override
	public Object next() {
		current=current.next;
		return current;
	}

	/**
	 * 从头结点遍历，找到最后一个节点，最后一个节点引用下一个元素。最后把current指向头结点。
	 * @param o 要添加的对象
	 */
	@Override
	public void add(Object o) {
		Node node=new LinkedList.Node();
		node.data=o;
		if (head==null) {
			head=node;
			current=head;
			size++;
			return;
		}
		while (hasNext()) {
			next();
		}
		current.next=node;
		current=head;
		size++;
	}

	/**
	 * 在指定的位置插入一个元素，并把当前节点指向head。
	 * @throws ListIndexException 
	 */
	@Override
	public void add(int index, Object o) throws ListIndexException {
		if (index<0||index>size) {
			throw new ListIndexException("index必须在[0,size]之间");
		}
		Node node=new LinkedList.Node();
		node.data=o;
		if (index==0) {
			node.next=current;
			head=node;
			current=node;
		}
		else{
			int i=0;
			while (i<index&&hasNext()) {
				if (i+1==index) {
					node.next=current.next;
					current.next=node;
				}
				next();
				i++;
			}
			if (i==index) {
				add(o);
			}		
		}
		size++;
		current=head;
	}

	/* (non-Javadoc)
	 * @see nusub.coding2017.basic.List#get(int)
	 */
	@Override
	public Object get(int index) throws ListIndexException {
		if (index<0||index>=size) {
			throw new ListIndexException("index必须在[0,size)之间");
		}
		if (index==0) {
			return current;
		}
		int i=0;
		while (i<index&&hasNext()) {
			i++;
			next();
		}
		Node node=current;
		current=head;
		return node;
	}

	/* (non-Javadoc)
	 * @see nusub.coding2017.basic.List#remove(int)
	 */
	@Override
	public Object remove(int index) throws ListIndexException {
		if (index<0||index>=size) {
			throw new ListIndexException("index必须在[0,size)之间");
		}
		if (size==0) {
			return null;
		}
		Node node=null;
		if (index==0) {
			node=current;
			current=current.next;
		}
		else if (index<size-1) {
			int i=0;
			while (hasNext()) {
				if (i+1==index) {
					node=current.next;
					current.next=current.next.next;
				}
				i++;
				next();
			}
		}
		else {
			int i=0;
			while (hasNext()) {
				if (i+1==index) {
					node=current.next;
					current.next=null;
				}
				i++;
				next();
			}
		}
		size--;
		return node;
	}

	/* (non-Javadoc)
	 * @see nusub.coding2017.basic.List#size()
	 */
	@Override
	public int size() {
		return size;
	}

	private static class Node{
		@SuppressWarnings("unused")
		Object data;
		Node next;
	}
}
