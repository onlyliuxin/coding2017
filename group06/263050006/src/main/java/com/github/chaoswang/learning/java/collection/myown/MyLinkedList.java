package com.github.chaoswang.learning.java.collection.myown;

import java.util.NoSuchElementException;

public class MyLinkedList<E> {
	private int size = 0;
	private Node head = null;
	private Node tail = null;
	
	//��
	public void add(E element){
		Node tmp = new Node(element, null);
		if(tail == null){
			head = tmp;
		}else{
			tail.next = tmp;;
		}
		tail = tmp;
		size++;
	}
	
	public void add(int index, E element){
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException();
		}
		Node tmpBefore = getElement(index -1);
		Node tmpAfter = getElement(index);
		Node tmp = new Node(element, tmpAfter);
		tmpBefore.next = tmp;
		
	}
	
	public void addFirst(E element){
		Node tmp = new Node(element, null);
		if(head != null){
			tmp.next = head;
		}else{
			tail = tmp;
		}
		head = tmp;
	}
	
	public E removeFirst(){
		if(size <= 0){
			throw new NoSuchElementException();
		}
		Node tmp = head;
		head = head.next; 
		return (E)tmp.element;
	}
	
	//��
	public E remove(int index) {
		if(index < 0 || index >= size()){
			throw new IndexOutOfBoundsException();
		}
		Node tmpBeore = this.getElement(index-1);
		Node tmp = this.getElement(index);
		Node tmpNext = this.getElement(index+1);
		tmpBeore.next = tmpNext;
		size--;
		return (E)tmp.element;
	}
	
	//��
	public E get(int index){
		return (E)this.getElement(index).element;
	}
	
	public int size() {
		return size;
	}
	
	@Override
	public String toString() {
		if(head == null){
			return "[]";
		}
		StringBuffer sb = new StringBuffer("[");
		Node tmp = head;
		while(tmp != null){
			sb.append(tmp.element.toString());
			sb.append(",");
			tmp = tmp.next;
		}
		String returnStr = sb.toString();
		returnStr = returnStr.substring(0, returnStr.length()-1);
		return returnStr + "]";
	}
	
	private Node getElement(int index) {
		Node tmp = head;
		for(int i=0;i<index;i++){
			tmp = tmp.next;
		}
		return tmp;
	}
	
	/*����static�ͺ�ʵ������,��Ȼ���Թ���������ÿ��Node�ж��������һ��ָ����Χ������ã��˷�ʱ��Ϳռ�
	 * ����Oracle�ٷ���˵����
	 * Nested classes are divided into two categories: static and non-static. 
	 * Nested classes that are declared static are called static nested classes. 
	 * Non-static nested classes are called inner classes.
	 * �������Ͽ���һ������Ϊ��̬Ƕ���࣬һ������Ϊ�ڲ��ࡣ
	 * ������ĽǶȽ����������ģ�
	 * ʲô��Ƕ�ף�Ƕ�׾����Ҹ���û��ϵ���Լ�������ȫ�������ڣ������Ҿ������Ŀ���һ�£�������һ�����Լ�����TM�������
	 * ʲô���ڲ����ڲ������������һ���֣����˽��㣬��֪�����ȫ����û�����û���ҡ��������ڲ�����������ⲿ��������Ϊǰ��ģ�
	 */
	private static class Node{
		private Object element = null;
		private Node next = null;
		//˫������
//		private Node previos = null;

		public Node(Object element, Node next) {
			this.element = element;
			this.next = next;
		}
	}
}
