package com.work.week01;

import java.io.Serializable;


public class MyLinkedList<E> implements MyList<E>, Serializable{

	private static final long serialVersionUID = 8700137302944494769L;
	
	transient int size = 0;
	
	transient MyNode<E> head;
	transient MyNode<E> last;
	
	public MyLinkedList(){
		head = new MyNode<E>(null, null);
		last = new MyNode<E>(null, null);
	}

	@Override
	public boolean add(E element) {
		if(head.element == null){
			head = new MyNode<E>(element, null);
			last = head;
		}else{
			MyNode<E> node = new MyNode<E>(element, null);
			last.next = node;
			last = node;
		}
		size++;
		return true;
	}

	@Override
	public void add(int index, E element) {
		if(index < 0 || index -size > 0){
			throw new IndexOutOfBoundsException("超出链表长度");
		}
		if(index == 0){
			MyNode<E> node = new MyNode<E>(element, null);
			node.next = head;
			head = node;
		}else{
			MyNode<E> leftNode = getIndexNode(index-1);
			MyNode<E> node = new MyNode<E>(element, null);
			node.next = leftNode.next;
			leftNode.next = node;
		}
		size++;
	}
	
	private MyNode<E> getIndexNode(int index){
		MyNode<E> node = head;
		for(int i=0; i<index; i++){
			node = node.next;
		}
		return node;
	}

	@Override
	public E get(int index) {
		if(index < 0 || index - size >= 0){
			throw new IndexOutOfBoundsException("超出链表长度");
		}
		MyNode<E> node = getIndexNode(index);
		return node.element;
	}

	@Override
	public E remove(int index) {
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException("超出链表长度");
		}
		if(index == 0){//移除头结点
			MyNode<E> node = head;
			head = head.next;
			node.next = null;
			size--;
			return node.element;
		}else{
			MyNode<E> leftNode = getIndexNode(index-1);
			MyNode<E> node = leftNode.next;		//欲移除的节点
			leftNode.next = node.next;
			node.next = null;
			size--;
			return node.element;
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void addFirst(E element){
		add(0, element);
	}
	
	public void addLast(E element){
		add(size, element);
	}
	
	public void removeFirst(){
		remove(0);
	}
	
	public void removeLast(){
		remove(size-1);
	}

	private static class MyNode<E>{
        E element;
        MyNode<E> next;

        MyNode(E element, MyNode<E> next) {
            this.element = element;
            this.next = next;
        }

    }

	@Override
	public MyIterator<E> iterator() {
		return new MyIter();
	}
	
	private class MyIter implements MyIterator<E>{
		
		int flag = 0;
		
		public MyIter(){
			flag = size;
		}

		@Override
		public boolean hasNext() {
			return flag > 0;
		}

		@Override
		public E next() {
			if(!hasNext()){
				throw new IndexOutOfBoundsException("索引值超出链表范围");
			}
			return get(size-(flag--));
		}
	}
	
	public static void main(String[] args) {
		MyLinkedList<String> link = new MyLinkedList<String>();
		link.add("1");
		link.add("2");
		link.add("3");
		link.add("4");
		link.add(3, "1");
		link.removeFirst();
		System.out.println("size="+link.size());
		MyIterator<String> itr = link.iterator();
		while(itr.hasNext()){
			System.out.println(itr.next());
		}
		link.remove(4);
	}
}
